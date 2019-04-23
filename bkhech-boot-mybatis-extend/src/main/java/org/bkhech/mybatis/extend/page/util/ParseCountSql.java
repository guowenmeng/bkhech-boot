package org.bkhech.mybatis.extend.page.util;

import org.bkhech.mybatis.extend.page.dialect.GenericDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guowm on 2017/3/10.
 */
public class ParseCountSql {

    private static final Logger logger = LoggerFactory.getLogger(GenericDialect.class);

    public static String parseSql(String sql) {
        sql = compress(sql);
        if (sql.indexOf("select distinct") == -1) {
            int index = getFirstFormIndex(sql);
            int orderIndex = sql.lastIndexOf("order by");
            orderIndex = orderIndex != -1 ? orderIndex : sql.length();

            if (logger.isDebugEnabled()) {
                logger.debug("come from mybatis-extend , and sql = {}", sql);
            }
            String selectBody = sql.substring(index, orderIndex);
            return "select count(1)" + selectBody;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("come from mybatis-extend , and sql = {}", sql);
            }
            return StringTools.append("select count(1) from (", sql, ") tmp_count");
        }
    }

    private static String compress(String sql) {
        return sql.toLowerCase().replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }

    private static int getFirstFormIndex(String sql) {
        String regex = "\\s+FROM\\s+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            return matcher.start(0);
        }
        return 0;
    }

}
