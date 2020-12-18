package com.bkhech.elasticjob.test;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author guowm
 * @date 2020/12/18
 * @description 作业开发
 */
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                int i = 1/0;
                System.out.println(context.getShardingItem());
                break;
            case 1:
                // do something by sharding item 1
                System.out.println(context.getShardingItem());
                break;
            case 2:
                // do something by sharding item 2
                System.out.println(context.getShardingItem());
                break;
            // case n: ...
        }
    }
}