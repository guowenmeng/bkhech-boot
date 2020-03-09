package com.bkhech.tools;

import org.springframework.context.ApplicationListener;

public class GuoListener implements ApplicationListener<GuoEvent> {

    @Override
    public void onApplicationEvent(GuoEvent event) {
        Object source = event.getSource();
        System.out.println("source = " + source);
        System.out.println("event = " + event.getName());
    }
}
