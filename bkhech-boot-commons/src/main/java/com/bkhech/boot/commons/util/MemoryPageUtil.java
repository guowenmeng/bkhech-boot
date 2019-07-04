package com.bkhech.boot.commons.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/1/17
 * 内存分页
 */
public class MemoryPageUtil {

    /**
     * 根据传入的List和页码以及每页尺寸，返回分页后的List
     * @param source 全量的List数据
     * @param page 页码
     * @param limit 每页数据条数
     * @param <T>
     * @return 返回分页后的对应页码页面的数据
     */
    public static <T> List<T> page(List<T> source,int page,int limit){
        List list = new ArrayList<T>();
        Collections.addAll(list,page(source.toArray(),page,limit));
        return list;
    }

    /**
     * 根据传入的数组和页码以及每页尺寸，返回分页后的数组
     * @param source 全量数据的数组
     * @param page 页码
     * @param limit 每页数据条数
     * @param <T>
     * @return 返回分页后的对应页码页面的数据
     */
    public static <T> T[] page(T[] source, int page, int limit) {
        if(null==source || source.length == 0) {
            return (T[]) Array.newInstance(source.getClass().getComponentType(), 0);
        }
        if (page <= 0) {
            page = 1;
        }
        int from = (page - 1) * limit;
        int to = page * limit;
        if(to > source.length) {
            to = source.length;
        }
        if(from >= source.length || to <= from) {
            return (T[]) Array.newInstance(source.getClass().getComponentType(), 0);
        }
        return Arrays.copyOfRange(source, from, to);
    }


//    public static void main(String[] args) {
//        List source = new ArrayList<>();
//        IntStream.range(1,12).forEach(i -> source.add(i));
//
//        List page = page(source, 1, 5);
//        page.forEach(item -> System.out.println(item));
//    }

}
