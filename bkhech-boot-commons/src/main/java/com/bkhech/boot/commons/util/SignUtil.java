package com.bkhech.boot.commons.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.MultiValueMap;

import java.util.*;

public class SignUtil {

    /**
     * 校验签名
     *   生成算法：对上面所有参数按照key自然排序,然后生成a=v&b=v1,然后md5(APP_KEY + APP_SECRET_KEY + "a=v&b=v1")
     * @param requestSign
     * @param params
     * @param appKey 服务端配置的appkey值
     * @param secretKey 服务端配置的secretKey值
     * @return true 成功 ，false 失败
     */
    public static boolean verfityRequest(String requestSign, Map<String, String> params, String appKey, String secretKey) {
        String[] sortedParams = new String[params.size()];
        params.keySet().toArray(sortedParams);
        Arrays.sort(sortedParams);
        StringBuilder sb = new StringBuilder();
        for (String key: sortedParams) {
            sb.append(String.format("&%s=%s", key, params.get(key)));
        }
        String md5Str = appKey + secretKey + sb.toString().substring(1);
        return DigestUtils.md5DigestAsHex(md5Str.getBytes()).equals(requestSign);
    }

    /**
     * 生成支付请求签名
     *  算法：1.将params中key在unsetKeys中的值过滤；
     *      2.将剩余params中的value值按自然顺序排序；
     *      3.result = paySignKey + value(排序后的value的连接字符串);
     *      5.sign = md5(result);
     * @param params
     * @param paySignKey
     * @return
     */
    public static String genSign(MultiValueMap<String, String> params, String paySignKey) {
        Set<String> unsetKeys = new HashSet<>();
        unsetKeys.add("sign");
        unsetKeys.add("key");

        ArrayList<String> signValues = new ArrayList<>(params.size());
        for (String pk: params.keySet()) {
            if (!unsetKeys.contains(pk)) {
                signValues.add(params.get(pk).get(0));
            }
        }
        Collections.sort(signValues);
        StringBuffer sb = new StringBuffer(200);
        sb.append(paySignKey);
        signValues.forEach(sb::append);
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
    }

}
