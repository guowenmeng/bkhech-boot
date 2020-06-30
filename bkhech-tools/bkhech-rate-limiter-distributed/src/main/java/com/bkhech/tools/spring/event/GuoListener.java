package com.bkhech.tools.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author guowm
 * @date 2020/6/30
 * @description spring事件机制使用
 */
@Component
public class GuoListener implements ApplicationListener<GuoEvent> {

    @Override
    public void onApplicationEvent(GuoEvent event) {
        Object source = event.getSource();
        System.out.println("source = " + source);
        System.out.println("event = " + event.getName());
    }
}
