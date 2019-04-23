package com.bkhech.boot.configure.common.builder.table.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: TableResponse
 * @author guowm 2018/9/19
 */
public class TableResponse<T extends List<?>> {

    private T body;
    private List<Map<String, String>> thead = new ArrayList<>();

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public List<Map<String, String>> getThead() {
        return thead;
    }

    public void setThead(List<Map<String, String>> thead) {
        this.thead = thead;
    }
}
