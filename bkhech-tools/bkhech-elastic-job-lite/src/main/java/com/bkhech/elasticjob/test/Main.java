package com.bkhech.elasticjob.test;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;

import java.io.IOException;

/**
 * @author guowm
 * @date 2020/12/18
 * @description
 */
public class Main {


    public static void main(String[] args) throws IOException {

        //作业调度
        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();

        System.in.read();

    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration("127.0.0.1:2181", "my-job");
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(10*1000);
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        regCenter.init();
        return regCenter;
    }

    //作业配置
    private static JobConfiguration createJobConfiguration() {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration
                .newBuilder("MyJob", 1)
                .cron("0/5 * * * * ?")
                // 默认
                .jobErrorHandlerType("LOG")
                .build();
        return jobConfig;
    }

}
