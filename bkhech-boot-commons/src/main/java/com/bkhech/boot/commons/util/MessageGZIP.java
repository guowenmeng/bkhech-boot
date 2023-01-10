package com.bkhech.boot.commons.util;

import cn.hutool.core.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author guowm
 * @date 2020/6/28
 * 对字符串进行gzip压缩，进行Base64加、解密
 */
public class MessageGZIP {

    private static Logger log = LoggerFactory.getLogger(MessageGZIP.class);

    /**
     * 将字符串压缩后Base64
     *
     * @param primStr 待加压加密函数
     * @return
     */
    public static String gzipString(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gout = new GZIPOutputStream(out)) {
            gout.write(primStr.getBytes("UTF-8"));
            gout.flush();
            return Base64.encode(out.toByteArray());
        } catch (IOException e) {
            log.error("对字符串进行加压加密操作失败：", e);
            return null;
        }
    }

    /**
     * 将压缩并Base64后的字符串进行解密解压
     *
     * @param compressedStr 待解密解压字符串
     * @return
     */
    public static final String unGzipString(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        String decompressed = null;
        byte[] compressed = Base64.decode(compressedStr);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(compressed);
             GZIPInputStream gin = new GZIPInputStream(in)) {

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString("UTF-8");
        } catch (IOException innerE) {
            log.error("对字符串进行解密解压操作失败：", innerE);
            decompressed = null;
        }
        return decompressed;
    }

}
