## 常用工具类
1. StopWatch统计时间
- 有时候需要记录一段代码执行时间，常见的方法就是打印当前时间与执行完时间的差值，
  缺点是这样如果执行大量测试的话就很麻烦，并且不直观，如果想对执行的时间做进一
  步控制，则需要在程序中很多地方修改

> org.springframework.util.StopWatch
> org.apache.commons.lang3.time.StopWatch
> com.google.common.base.Stopwatch 

```java
import org.springframework.util.StopWatch
   		StopWatch sw = new StopWatch();
        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();
        
        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

        System.out.println(sw.prettyPrint());
        System.out.println(sw.getTotalTimeMillis());
        System.out.println(sw.getLastTaskName());
        System.out.println(sw.getLastTaskInfo());
        System.out.println(sw.getTaskCount());
```

```java
import org.apache.commons.lang3.time; 
      StopWatch watch=new  StopWatch();
      watch.start();
      watch.stop();
      watch.getSplitTime(); //时间处理只支持ms
```

```java
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch; 
        Stopwatch watch = new Stopwatch();
        watch.start();
        watch.stop();
        watch.elapsed(TimeUnit.MINUTES); //时间可以支持多种格式，可以自由选择
```

