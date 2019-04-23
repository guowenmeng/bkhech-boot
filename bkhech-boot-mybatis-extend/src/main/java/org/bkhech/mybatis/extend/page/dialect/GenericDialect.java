package org.bkhech.mybatis.extend.page.dialect;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.RowBounds;
import org.bkhech.mybatis.extend.page.constant.DialectInfo;
import org.bkhech.mybatis.extend.page.constant.PageConstant;
import org.bkhech.mybatis.extend.page.param.Page;
import org.bkhech.mybatis.extend.page.result.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by guowm on 2017/3/6.
 */
public abstract class GenericDialect implements Dialect {

    private static final Logger logger = LoggerFactory.getLogger(GenericDialect.class);

    public static Dialect getDialectInstance(Properties properties)
            throws IllegalAccessException, InstantiationException {
        String dialectName = properties.getProperty(PageConstant.DIALECT);
        Class dialectClass = DialectInfo.getClazzByName(dialectName);
        return (Dialect) dialectClass.newInstance();
    }

    @Override
    public boolean canBePaged(MappedStatement ms, String sql, Object parameter) {
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
        if (SqlCommandType.SELECT == sqlCommandType) {
            if (sql.trim().toUpperCase().endsWith("FOR UPDATE")) {
                throw new RuntimeException("for update语句不支持分页");
            }

            if (getPageParameter(parameter) == null) {
                return false;
            }
        } else {
            throw new RuntimeException("非select语句不支持分页");
        }
        return true;
    }

    @Override
    public Page getPageParameter(Object parameter) {
        if (parameter instanceof Map) {
            Map map = (Map) parameter;
            if (map.containsKey(PageConstant.PAGE_KEY)) {
                Object pageObj = map.get(PageConstant.PAGE_KEY);

                if (pageObj != null && (pageObj instanceof Page)) {
                    return (Page) pageObj;
                }
            }
        }
        return null;
    }

    @Override
    public BoundSql getCountSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                                Map<String, Object> additionalParameters) {
        String countSql = getCountSql(boundSql.getSql());

        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        return countBoundSql;
    }

    @Override
    public BoundSql getPageSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                               CacheKey pageKey, RowBounds rowBounds , Map<String, Object> additionalParameters, List<Map<String, Page.OrderType>> orders) {
        String pageSql = getPageSql(boundSql.getSql(), rowBounds, pageKey, orders);

        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        return pageBoundSql;
    }

    @Override
    public PageList buildPageList(List resultList, Page page, int totalRows) {
        if (logger.isDebugEnabled()) {
            logger.debug("resultList : " + resultList +" \r\n| page.getPage_num() : " + page.getPage_num() + " \r\n| page.getPage_size() : " + page.getPage_size() +" \r\ntotalRows : " + totalRows);
        }
        return new PageList(resultList, page.getPage_num(), page.getPage_size(), totalRows);
    }

    public abstract String getPageSql(String sql, RowBounds rowBounds, CacheKey pageKey, List<Map<String, Page.OrderType>> orders);

    public abstract String getCountSql(String sql);

}
