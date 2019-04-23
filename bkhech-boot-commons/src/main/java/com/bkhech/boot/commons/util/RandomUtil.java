package com.bkhech.boot.commons.util;

import java.util.Random;

/**
 * RandomUtil
 *
 * Created by guowm on 18-5-29.
 */
public class RandomUtil {

    public static String CHAR_UPPERCASE  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String CHAR_LOWERCASE  = "abcdefghijklmnopqrstuvwxyz";
    public static String CHAR_NUMBERS    = "0123456789";
    public static String CHAR_SPECIAL    = "~@#^()[]*$-+?_&=!%{}/";
    public static String CHAR_DEFAULT	 = CHAR_UPPERCASE + CHAR_LOWERCASE + CHAR_NUMBERS;

    public static String generate(int length) {
        return generate(CHAR_DEFAULT, length);
    }

    public static String generate(int upperCase, int lowerCase, int numbers, int special) {
        StringBuffer password = new StringBuffer();
        password.append(generate(CHAR_UPPERCASE, upperCase));
        password.append(generate(CHAR_LOWERCASE, lowerCase));
        password.append(generate(CHAR_NUMBERS, numbers));
        password.append(generate(CHAR_SPECIAL, special));
        return generate(password.toString(), password.length());
    }

    public static String generate(final String charString, int length) {
        if (length < 1) {
            return "";
        }
        int i = 0;
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        while (i < length) {
            int randomPosition = random.nextInt(charString.length());
            if (result.indexOf(String.valueOf(charString.charAt(randomPosition))) < 0) {
                result.append(charString.charAt(randomPosition));
                i++;
            }
        }
        return result.toString();
    }

}
