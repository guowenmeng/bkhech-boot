package com.bkhech.boot.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * DigestUtil
 *
 * Created by guowm on 18-5-22.
 */
public class DigestUtil {

    public static String digestMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static String mapToParams(Map<?, ?> map) {
        StringBuffer value = new StringBuffer();
        Map treeMap = new TreeMap<>();
        treeMap.putAll(map);
        treeMap.forEach((k, v) -> {
            if (v != null && !"".equals(v.toString())) {
                value.append(k.toString()).append("=").append(v).append("&");
            }
        });
        return value.length() > 0 ? value.substring(0, value.lastIndexOf("&")) : null;
    }

    public static String mapToURLParams(Map<?, ?> map) {
        Map treeMap = new TreeMap<>();
        map.forEach((k, v) -> {
            try {
                treeMap.put(k, URLEncoder.encode(v.toString(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return mapToParams(treeMap);
    }
}