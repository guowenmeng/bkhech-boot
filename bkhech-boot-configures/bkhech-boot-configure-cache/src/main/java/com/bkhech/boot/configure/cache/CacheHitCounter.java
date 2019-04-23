package com.bkhech.boot.configure.cache;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

 /**
  * Description: CacheHitCounter
  * @author guowm 2018/10/12
  */
public class CacheHitCounter {

    private static final AtomicLongFieldUpdater<CacheHitCounter> hit_count_updater = AtomicLongFieldUpdater.newUpdater(CacheHitCounter.class, "hitCount");
    private static final List<CacheHitCounter> hitCounters = Lists.newArrayList();

    private Object key;
    private volatile long hitCount;

    public CacheHitCounter(Object key, long hitCount) {
        this.key = key;
        this.hitCount = hitCount;
    }

    public static long getHitCount(Object key) {
        return getHitCounter(key).hitCount;
    }

    public static long incrementAndGet(Object key) {
        return hit_count_updater.incrementAndGet(getHitCounter(key));
    }

    public static void reset(Object key) {
        hit_count_updater.set(getHitCounter(key), 0L);
    }

    private static CacheHitCounter getHitCounter(Object key) {
        CacheHitCounter hitCounter;
        if (hitCounters.stream().filter(i -> i.key.equals(key)).count() > 0) {
            hitCounter = hitCounters.stream().filter(i -> i.key.equals(key)).findFirst().get();
        } else {
            hitCounter = new CacheHitCounter(key, 0);
            hitCounters.add(hitCounter);
        }
        return hitCounter;
    }
}
