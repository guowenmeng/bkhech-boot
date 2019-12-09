package com.bkhech.boot.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * 返回一个随机数 或者批次数
     * @param origin the least value returned
     * @param bound the upper bound (exclusive)
     * @return a pseudorandom {@code int} value = 当前时间+ bound和origin之间的一个随机数值
     *   时间format是yyyyMMddHHmmss
     * @throws IllegalArgumentException if {@code origin} is greater than
     *         or equal to {@code bound}
     */
    public static String generateBatchNo(int bound, int origin) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + threadLocalRandom.nextInt(bound, origin);
    }

}
