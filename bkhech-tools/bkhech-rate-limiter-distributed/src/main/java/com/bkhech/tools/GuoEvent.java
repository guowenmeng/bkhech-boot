package com.bkhech.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

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
