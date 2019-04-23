package com.bkhech.boot.commons.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guowm on 2017/8/8.
 */
public class PatternUtil {

    public static final String CASE_NUMBER = "[0-9]*(\\.?)[0-9]*";

    public static final String CASE_DATETIME = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    public static final String CASE_YEAR = "(\\d{4})-(\\d+)-(\\d+).*";

    public static final String CASE_FILTER = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public static Matcher matcher(String value, String cases) {
        Pattern pattern = Pattern.compile(cases);
        return pattern.matcher(value);
    }

    public static boolean match(String value, String cases) {
        return matcher(value, cases).matches();
    }

    public static boolean isNumber(String value) {
        return match(value, CASE_NUMBER);
    }

    public static boolean isDateTime(String value) {
        return match(value, CASE_DATETIME);
    }

    public static boolean isRealDateTime(String value) {
        if(match(value, CASE_DATETIME)) {
            Matcher matcher = matcher(value, CASE_YEAR);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    public static String filter(String value) {
        return matcher(value, CASE_FILTER).replaceAll("").trim();
    }

    public static String unicodeFilter(String str) {
        if (str == null) {
            return str;
        }
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isISOControl(str.charAt(i))) {
                value.append(str.charAt(i));
            }
        }
        return matcher(value.toString().trim(), "\\\\|\t|\r|\n|\"").replaceAll("").toLowerCase();
    }

}
