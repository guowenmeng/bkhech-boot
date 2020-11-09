package com.bkhech.boot.sample.swagger.dto;

import com.bkhech.boot.sample.swagger.model.event.EventLog;
import org.bkhech.mybatis.extend.page.param.Page;

/**
 * @author guowm
 * @date 2020/11/9
 * @description
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
