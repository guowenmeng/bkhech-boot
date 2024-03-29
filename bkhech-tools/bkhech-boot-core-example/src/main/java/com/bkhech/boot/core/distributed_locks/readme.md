# 分布式锁实现
## redis实现
- 缺点： 通常我们为了实现 Redis 的高可用，一般都会搭建 Redis 的集群模式，比如给 Redis 节点挂载一个或多个 slave 从节点，
  然后采用哨兵模式进行主从切换。 但由于 Redis 的主从模式是异步的，所以可能会在数据同步过程中，master 主节点宕机，slave 从节
  点来不及数据同步就被选举为 master 主节点，从而导致数据丢失，大致过程如下：
1. 用户在 Redis 的 master 主节点上获取了锁；
2. master 主节点宕机了，存储锁的 key 还没有来得及同步到 slave 从节点上；
3. slave 从节点升级为 master 主节点；
4. 用户从新的 master 主节点获取到了对应同一个资源的锁，同把锁获取两次。

ok，然后为了解决这个问题，Redis 作者提出了 RedLock 算法，步骤如下（五步）：

在下面的示例中，我们假设有 5 个完全独立的 Redis Master 节点，他们分别运行在 5 台服务器中，可以保证他们不会同时宕机。

1. 获取当前 Unix 时间，以毫秒为单位。
2. 依次尝试从 N 个实例，使用相同的 key 和随机值获取锁。在步骤 2，当向 Redis 设置锁时，客户端应该设置一个网络连接和响应超时时间，这个超时时间应该小于锁的失效时间。例如你的锁自动失效时间为 10 秒，则超时时间应该在 5-50 毫秒之间。这样可以避免服务器端 Redis 已经挂掉的情况下，客户端还在死死地等待响应结果。如果服务器端没有在规定时间内响应，客户端应该尽快尝试另外一个 Redis 实例。
3. 客户端使用当前时间减去开始获取锁时间（步骤 1 记录的时间）就得到获取锁使用的时间。当且仅当从大多数（这里是 3 个节点）的 Redis 节点都取到锁，并且使用的时间小于锁失效时间时，锁才算获取成功。
4. 如果取到了锁，key 的真正有效时间等于有效时间减去获取锁所使用的时间（步骤 3 计算的结果）。
5. 如果因为某些原因，获取锁失败（没有在至少 N/2+1 个Redis实例取到锁或者取锁时间已经超过了有效时间），客户端应该在所有的 Redis 实例上进行解锁（即便某些 Redis 实例根本就没有加锁成功）。

到这，基本看出来，只要是大多数的 Redis 节点可以正常工作，就可以保证 Redlock 的正常工作。这样就可以解决前面单点 Redis 的情况下我们讨论的节点挂掉，由于异步通信，导致锁失效的问题。

但是细想后， Redlock 还是存在如下问题：

假设一共有5个Redis节点：A, B, C, D, E。设想发生了如下的事件序列：

1. 客户端1成功锁住了A, B, C，获取锁成功（但D和E没有锁住）。
2. 节点C崩溃重启了，但客户端1在C上加的锁没有持久化下来，丢失了。
3. 节点C重启后，客户端2锁住了C, D, E，获取锁成功。
4. 这样，客户端1和客户端2同时获得了锁（针对同一资源）。
哎，还是不能解决故障重启后带来的锁的安全性问题…

针对节点重后引发的锁失效问题，Redis 作者又提出了 延迟重启 的概念，大致就是说，一个节点崩溃后，不要立刻重启他，而是等到一定的时间后再重启，等待的时间应该大于锁的过期时间，采用这种方式，就可以保证这个节点在重启前所参与的锁都过期，听上去感觉 延迟重启 解决了这个问题…

但是，还是有个问题，节点重启后，在等待的时间内，这个节点对外是不工作的。那么如果大多数节点都挂了，进入了等待，就会导致系统的不可用，因为系统在过期时间内任何锁都无法加锁成功…
巴拉巴拉那么多，关于 Redis 分布式锁的缺点显然进入了一个无解的步骤，包括后来的 神仙打架事件（Redis 作者 antirez 和 分布式领域专家 Martin Kleppmann）…

总之，首先我们要明确使用分布式锁的目的是什么？

无外乎就是保证同一时间内只有一个客户端可以对共享资源进行操作，也就是共享资源的原子性操作。

总之，在 Redis 分布式锁的实现上还有很多问题等待解决，我们需要认识到这些问题并清楚如何正确实现一个 Redis 分布式锁，然后在工作中合理的选择和正确的使用分布式锁。

目前我们项目中也有在用分布式锁，也有用到 Redis 实现分布式锁的场景，然后有的小伙伴就可能问，啊，你们就不怕出现上边提到的那种问题吗~

其实实现分布式锁，从中间件上来选，也有 Zookeeper 可选，并且 Zookeeper 可靠性比 Redis 强太多，但是效率是低了点，如果并发量不是特别大，追求可靠性，那么肯定首选 Zookeeper。
如果是为了效率，就首选 Redis 实现。
  
- zookeeper