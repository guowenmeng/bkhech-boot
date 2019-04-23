package org.bkhech.mybatis.extend.page.dialect.impl;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.session.RowBounds;
import org.bkhech.mybatis.extend.page.util.ParseCountSql;
import org.bkhech.mybatis.extend.page.util.StringTools;
import org.bkhech.mybatis.extend.page.dialect.GenericDialect;
import org.bkhech.mybatis.extend.page.param.Page;

import java.util.List;
import java.util.Map;


/**
 * Created by guowm on 2017/3/6.
 */
public class MySqlGenericDialect extends GenericDialect {

    @Override
    public String getPageSql(String sql, RowBounds rowBounds, CacheKey pageKey, List<Map<String, Page.OrderType>> orders) {
        pageKey.update(rowBounds.getLimit());
        if (sql.toLowerCase().indexOf("order by") == -1 && orders.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Map<String, Page.OrderType> map : orders) {
                for (Map.Entry<String, Page.OrderType> entry : map.entrySet()) {
                    builder.append(entry.getKey()).append(" ").append(entry.getValue().toString().toLowerCase()).append(", ");
                }
            }
            if (builder.length() > 0) {
                String orderSql = builder.substring(0, builder.lastIndexOf(", "));
                sql += " order by " + orderSql;
            }
        }
        return StringTools.append(sql, " limit ", rowBounds.getOffset(), " , ", rowBounds.getLimit());
    }

    @Override
    public String getCountSql(String sql) {
        return ParseCountSql.parseSql(sql);
    }

}
