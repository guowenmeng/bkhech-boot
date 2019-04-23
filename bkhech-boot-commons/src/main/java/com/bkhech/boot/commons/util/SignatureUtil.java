package com.bkhech.boot.commons.util;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guowm on 2017/8/28.
 */
public class SignatureUtil {

    private static final String PUBLIC_KEY = "public_key";

    private static final String PRIVATE_KEY = "private_key";

    /**
     * 生成签名
     *
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, String privateKey) {
        return sign(content, strToPrivateKey(privateKey));
    }

    /**
     * @param content
     * @param privateKey
     * @return
     */
    public static String sign(String content, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(content.getBytes("utf-8"));
            return Base64.encodeBase64String(signature.sign()).replaceAll("\n|\r", "");
        } catch (Exception e) {
            return null;
        }
    }

    public static String sign(Map<?, ?> map, String privateKey) {
        String content = DigestUtil.mapToParams(map);
        return sign(content, privateKey);
    }

    /**
     * 验证签名
     *
     * @param content
     * @param publicKey
     * @return
     */
    public static boolean verify(String content, String sign, String publicKey) {
        return verify(content, sign, strToPublicKey(publicKey));
    }

    /**
     * @param content
     * @param publicKey
     * @return
     */
    public static boolean verify(String content, String sign, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            signature.update(content.getBytes("utf-8"));
            return signature.verify(Base64.decodeBase64(sign));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean verify(Map<?, ?> map, String sign, String publicKey) {
        String content = DigestUtil.mapToParams(map);
        return verify(content, sign, strToPublicKey(publicKey));
    }

    public static PublicKey strToPublicKey(String publicKeyPerm) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyPerm));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    public static PrivateKey strToPrivateKey(String privateKeyPerm) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyPerm));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> generator() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Map<String, String> map = new HashMap<>();
            map.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()).replaceAll("\n|\r", ""));
            map.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()).replaceAll("\n|\r", ""));
            return map;
        } catch (Exception e) {
            throw new RuntimeException("rsa key generator failed");
        }
    }

}
