package com.bkhech.boot.sample.swagger.dto;

import com.bkhech.boot.sample.swagger.model.event.EventLog;
import org.bkhech.mybatis.extend.page.param.Page;

/**
 * Description: EventLogDto
 * @author guowm 2018/9/20
 */
public class EventLogDto extends EventLog {


    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
