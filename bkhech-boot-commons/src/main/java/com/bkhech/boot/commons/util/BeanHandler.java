package com.bkhech.boot.commons.util;

import com.bkhech.boot.commons.annotation.TheadIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * bean 辅助工具
 *
 * Created by guowm on 2016/9/12.
 */
public class BeanHandler {


    public static <E, T> T copyProperties(E e, T t) {
        BeanUtils.copyProperties(e , t);
        return t;
    }

    public static <E, T> T copyProperties(E e, Class<T> clx) {
        try {
            return copyProperties(e, clx.newInstance());
        } catch (Exception ex) {
            return null;
        }
    }

    public static <E, T> List<T> copyProperties(List<E> eList, Class<T> clx) {
        List<T> tList = Lists.newArrayList();
        for (E e : eList) {
            tList.add(copyProperties(e, clx));
        }
        return tList;
    }

    public static Object getValue(Object source, String fieldName) {
        try {
            Class clazz = source.getClass();
            Method method = clazz.getMethod(convertGetterName(fieldName), null);
            return method.invoke(source, null);
        } catch (Exception e) {
            throw new RuntimeException("not found the field");
        }
    }

    public static void setValue(Object source, String fieldName, Object value) {
        try {
            Class clazz = source.getClass();

            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(convertSetterName(fieldName))) {
                    method.invoke(source, value);
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("set the field value error");
        }
    }

    public static String convertGetterName(String field) {
        char firstChar = field.charAt(0);
        return "get" + Character.toTitleCase(firstChar) + field.substring(1);
    }

    public static String convertSetterName(String field) {
        char firstChar = field.charAt(0);
        return "set" + Character.toTitleCase(firstChar) + field.substring(1);
    }

    public static <T> T[] convertArray(List<T> list, Class<?> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }

    public static <T> T[] convertArray(List<T> list) {
        Class<?> clazz = list.get(0).getClass();
        return convertArray(list, clazz);
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回一个String对象列表，反映由{@link Class}对象表示的clazz声明的所有属性，
     * 列表中的元素按照元素的自然顺便正向排列。这属性包括公共，受保护，默认（包）访问和私有字段，
     * 但不包括继承的字段。
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<String> getfieldList(Class<T> clazz) {
        List<String> list = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Objects.isNull(field.getAnnotation(TheadIgnore.class))) {
                String name = field.getName();
                if (!name.equals("serialVersionUID")) {
                    list.add(name);
                }
            }
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    /**
     * 返回一个String对象列表，反映由{@link Class}对象表示的clazz声明的所有属性，
     * 列表中的元素按照元素的自然顺便正向排列。这属性包括公共，受保护，默认（包）访问和私有字段，
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<String> getfieldListWithSuper(Class<T> clazz) {
        List<String> list = Lists.newArrayList();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                String fieldName = decapitalize(name.substring(3));
                try {
                    Field field = clazz.getField(fieldName);
                    if (Objects.isNull(field.getAnnotation(TheadIgnore.class))) {
                        list.add(fieldName);
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    throw new RuntimeException("NoSuchFieldException...");
                }
            }
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    /**
     * 基于JavaBeans规范: 前两个字母 AA aa
     * @param s
     * @return
     */
    private static String decapitalize(String s) {
        if (s == null || s.length() == 0) {
            //空处理
            return s;
        }
        if (s.length() > 1 && Character.isUpperCase(s.charAt(1)) && Character.isUpperCase(s.charAt(0))){
            //长度大于1，并且前两个字符大写时，返回原字符串
            return s;
        } else{
            //其他情况下，把原字符串的首个字符小写处理后返回
            char ac[] = s.toCharArray();
            ac[0] = Character.toLowerCase(ac[0]);
            return new String(ac);
        }
    }

}
