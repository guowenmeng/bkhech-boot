package com.bkhech.boot.core.bloomfilter;

import io.rebloom.client.Client;

/**
 * Redis bloomFilter
 *
 * @see {@literal https://github.com/RedisBloom/JRedisBloom}
 * @see {@literal https://blog.csdn.net/lifetragedy/article/details/103945885}
 * @author guowm
 * @date 2021/6/29
 */
public class RedisBloomFilterDemo {

    public static void main(String[] args) {
        Client client = new Client("192.168.85.125", 63790);

        client.add("simpleBloom", "Mark");
        // Does "Mark" now exist?
        client.exists("simpleBloom", "Mark"); // true
        client.exists("simpleBloom", "Farnsworth"); // False


    }

}
