package com.bkhech.tools.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author guowm
 * @date 2020/6/30
 * @description
 */
public class GuoEvent extends ApplicationContextEvent {

    private final String name;

    public GuoEvent(ApplicationContext source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
