package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 *
 * 和uses = MyMapperUtil.class作用类似
 * 粒度更细
 */
@Mapper
public interface TestQualifiedByNameMapper {

    TestQualifiedByNameMapper INSTANCE = Mappers.getMapper(TestQualifiedByNameMapper.class);

    /**
     * 字段类型不对应
     * @param loginRequest
     * @return
     *
     * 方式一: uses = MyMapperUtil.class 全局
     * 方式二：@Mapping(target = "code", expression = "java(stringToInteger(loginRequest.getCode()))")
     *          粒度更细
     * 方式三：@Mapping(target = "code", source = "code", qualifiedByName = "stringToInteger") 等价方式二
     */
//    @Mapping(target = "code", expression = "java(stringToInteger(loginRequest.getCode()))")
    @Mapping(target = "code", source = "code", qualifiedByName = "stringToInteger")
    @Mapping(source = "loginType", target = "loginCode")
    LoginData qualifiedByName(LoginRequest loginRequest);

    //方式二使用
    @Named("stringToInteger")
    default Integer stringToInteger(String string) {
        return string != null && !string.isEmpty() ? Integer.parseInt(string) : null;
    }


}
