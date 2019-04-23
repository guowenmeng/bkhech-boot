package org.bkhech.mybatis.extend.page.dialect.impl;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.session.RowBounds;
import org.bkhech.mybatis.extend.page.util.StringTools;
import org.bkhech.mybatis.extend.page.dialect.GenericDialect;
import org.bkhech.mybatis.extend.page.param.Page;

import java.util.List;
import java.util.Map;


/**
 * Created by guowm on 2017/3/6.
 */
public class OracleGenericDialect extends GenericDialect {

    @Override
    public String getPageSql(String sql, RowBounds rowBounds, CacheKey pageKey, List<Map<String, Page.OrderType>> orders) {
        int startRow = rowBounds.getOffset();
        int endRow = rowBounds.getOffset() + rowBounds.getLimit();
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
        if (startRow > 0) {
            sqlBuilder.append("SELECT * FROM ( ");
        }
        if (endRow > 0) {
            sqlBuilder.append(" SELECT TMP_PAGE.*, ROWNUM ROW_ID FROM ( ");
        }
        sqlBuilder.append(sql.toUpperCase());
        if (endRow > 0) {
            sqlBuilder.append(" ) TMP_PAGE WHERE ROWNUM <= ");
            sqlBuilder.append(endRow);
            pageKey.update(endRow);
        }
        if (startRow > 0) {
            sqlBuilder.append(" ) WHERE ROW_ID > ");
            sqlBuilder.append(startRow);
            pageKey.update(startRow);
        }
        return sqlBuilder.toString();
    }

    @Override
    public String getCountSql(String sql) {
        return StringTools.append("SELECT COUNT(0) FROM (", sql, ") TMP_COUNT");
    }
}
