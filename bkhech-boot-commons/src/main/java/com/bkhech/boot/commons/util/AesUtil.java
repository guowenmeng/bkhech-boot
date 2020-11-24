package com.bkhech.boot.commons.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author guowm
 * @date 2020/11/24
 * @description
 *  aes加/解密算法，用于数据加密
 */
public class AesUtil {

    private static final String SECRECY_KEY = "swq3iIHV3pAENG32";

    /**
     * 二进制转变为十六进制
     * @param buf
     * @return java.lang.String
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将十六进制转变为二进制
     * @param hexStr
     * @return byte[]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return new byte[]{};
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 加密(PKCS5Padding)
     * @param content
     * @return java.lang.String
     */
    public static String encrypt(String content) throws Exception {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRECY_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

//            return parseByte2HexStr(encryptedData);
            //更少的长度
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
            throw new Exception("加密失败");
        }
    }

    /**
     * 解密
     * @param content
     * @return java.lang.String
     */
    public static String decrypt(String content) throws Exception {
//        byte[] contentBytes = parseHexStr2Byte(content);
        byte[] contentBytes = new BASE64Decoder().decodeBuffer(content);

        try {
            SecretKeySpec key = new SecretKeySpec(SECRECY_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(contentBytes);
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("解密失败");
        }
    }

}