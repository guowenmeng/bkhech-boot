package com.bkhech.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 * 类型不相等时解决方案：
 * 根据入参和出参的类型去匹配映射字段
 *  String to Integer
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public class MyStringIntegerMapper {
    public Integer stringToInteger(String string) {
        return string != null && !string.isEmpty() ? Integer.parseInt(string) : null;
    }
}
