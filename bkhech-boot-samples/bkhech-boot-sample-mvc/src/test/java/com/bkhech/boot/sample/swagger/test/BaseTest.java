package com.bkhech.boot.sample.swagger.test;

import com.bkhech.boot.sample.swagger.Application;
import com.bkhech.boot.sample.swagger.model.event.EventLog;
import com.bkhech.boot.sample.swagger.service.event.EventLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BaseTest
 *
 * Created by guowm on 18-5-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

    @Autowired
    private EventLogService eventLogService;

    @Test
    public void cache() {
        eventLogService.getById(1);
        eventLogService.getById(1);
        eventLogService.getById(1);
        eventLogService.getById(1);
        eventLogService.getById(1);
        eventLogService.getById(1);
        eventLogService.getById(1);

        EventLog eventLog = new EventLog();
        eventLog.setGame_id(1);
        eventLog.setEvent("test");
        eventLog.setAd_id(1);
        eventLog.setData("test cache");
        eventLog.setServer_datetime(122222222L);
        eventLog.setServer_date(111);
        eventLog.setServer_hour(0);
        eventLogService.save(eventLog);

        eventLogService.getById(eventLog.getId());
        eventLogService.getById(eventLog.getId());
    }

}
