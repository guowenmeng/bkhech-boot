package com.bkhech.boot.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 重试器
 *
 * Created by guowm on 2016/11/05.
 */
public abstract class RetryWorker<T, P> {

    protected static final Logger log = LoggerFactory.getLogger(RetryWorker.class);

    private int retryTimes = 1;

    private int maxTimes = 3;

    private List<Integer> intervalList;

    protected ThreadLocal<P> params = new ThreadLocal<P>();

    protected T result = null;

    public RetryWorker() { }

    public RetryWorker(int retryTimes, int maxTimes) {
        this.retryTimes = retryTimes;
        this.maxTimes = maxTimes;
    }

    public RetryWorker(int retryTimes, int maxTimes, List<Integer> intervalList) {
        this.retryTimes = retryTimes;
        this.maxTimes = maxTimes;
        this.intervalList = intervalList;
    }

    public abstract T work() throws Exception;

    protected void stop() { }

    public boolean execute() {
        if (retryTimes > maxTimes) {
            stop();
            retryTimes = 1;
            return false;
        }

        try {
            result = work();
            return true;
        } catch (Exception e) {
            log.error("RetryWorker: execute failed, try again, retryTimes: " + retryTimes, e);

            try {
                if (intervalList != null) {
                    int index = retryTimes - 1;
                    if (index < intervalList.size()) {
                        Thread.sleep(intervalList.get(index));
                    }
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            retryTimes++;
            return execute();
        }
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(int maxTimes) {
        this.maxTimes = maxTimes;
    }

    public ThreadLocal<P> getParams() {
        return params;
    }

    public T getResult() {
        return result;
    }

    public void setIntervalList(List<Integer> intervalList) {
        this.intervalList = intervalList;
    }
}
