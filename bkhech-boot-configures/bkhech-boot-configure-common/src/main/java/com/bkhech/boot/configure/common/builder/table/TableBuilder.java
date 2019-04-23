package com.bkhech.boot.configure.common.builder.table;

import com.alibaba.fastjson.JSONObject;
import com.bkhech.boot.configure.common.builder.table.response.TablePageResponse;
import com.bkhech.boot.configure.common.builder.table.response.TableResponse;
import com.google.common.collect.Lists;
import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import org.bkhech.mybatis.extend.page.result.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.bkhech.mybatis.extend.page.constant.PageConstant.*;

/**
 * Description: TableBuilder
 * @author guowm 2018/9/19
 */
public class TableBuilder<V extends List<?>> implements BaseBuilder {

    private static Logger logger = LoggerFactory.getLogger(ResultResponse.class);
    private static final String PAGEDTO = "pagedto";
    private static final String DTO = "dto";

    private ConcurrentHashMap<String, TableResponse<V>> tableMap = new ConcurrentHashMap(){{
        put(PAGEDTO, new TablePageResponse());
        put(DTO, new TableResponse());
    }};

    private TableBuilder() {}

    /**
     * 填充表格头
     * @param thead
     * @return
     */
    public TableBuilder thead(List<Map<String, String>> thead) {
        tableMap.get(DTO).setThead(thead);
        tableMap.get(PAGEDTO).setThead(thead);
        return this;
    }

    /**
     * 填充表格数据内容
     * @param data
     * @return
     */
    public TableBuilder body(V data) {
        if (data instanceof PageList) {
            TablePageResponse tablePageResponse = (TablePageResponse)tableMap.get(PAGEDTO);

            PageList pageList = (PageList) data;
            List list = Lists.newArrayList();

            for (int i = 0; i < pageList.size(); i++) {
                list.add(pageList.get(i));
            }

            V pageData = (V) list;
            tablePageResponse.setBody(pageData);

            JSONObject page = new JSONObject(16, true);
            page.put(PAGE_SIZE_KEY, pageList.getPageSize());
            page.put(PAGE_NUM_KEY, pageList.getPageNum());
            page.put(TOTAL_PAGE_KEY, pageList.getTotalPage());
            page.put(TOTAL_ROWS_KEY, pageList.getTotalRows());
            tablePageResponse.setPage(page);
        } else {
            TableResponse tableResponse = tableMap.get(DTO);
            tableResponse.setBody(data);
        }
        return this;
    }

    public TableResponse<V> build() {
        TablePageResponse<V> tablePageResponse = (TablePageResponse) tableMap.get(PAGEDTO);
        TableResponse<V> tableResponse = tableMap.get(DTO);
        if (tablePageResponse.getPage() != null) {
            return tablePageResponse;
        }
        return tableResponse;
    }

    public static TableBuilder newBuilder() {
        return new TableBuilder();
    }

}
