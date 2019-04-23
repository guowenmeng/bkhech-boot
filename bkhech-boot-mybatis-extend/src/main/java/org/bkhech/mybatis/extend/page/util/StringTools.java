package org.bkhech.mybatis.extend.page.util;

/**
 * Created by guowm on 2017/3/7.
 */
public class StringTools {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    public static String append(Object... str){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            builder.append(str[i].toString());
        }
        return builder.toString();
    }

}
