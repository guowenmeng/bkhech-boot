package org.bkhech.mybatis.extend.page.dialect;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.bkhech.mybatis.extend.page.result.PageList;
import org.bkhech.mybatis.extend.page.param.Page;

import java.util.List;
import java.util.Map;

/**
 * 基础方言接口
 *
 * Created by guowm on 2017/3/6.
 */
public interface Dialect {

    boolean canBePaged(MappedStatement ms, String sql, Object parameter);

    Page getPageParameter(Object parameter);

    BoundSql getCountSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                        Map<String, Object> additionalParameters);

    BoundSql getPageSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                        CacheKey pageKey, RowBounds rowBounds, Map<String, Object> additionalParameters, List<Map<String, Page.OrderType>> orders);

    PageList buildPageList(List resultList, Page page, int totalRows);
}
