package com.bkhech.boot.configure.common.builder.table.response;

import java.util.List;
import java.util.Map;

/**
 * Description: TablePageResponse
 * @author guowm 2018/9/19
 */
public class TablePageResponse<V extends List<?>> extends TableResponse<V> {

    private Map<String, Object> page;

    public Map<String, Object> getPage() {
        return page;
    }

    public void setPage(Map<String, Object> page) {
        this.page = page;
    }
}
