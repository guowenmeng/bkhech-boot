package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
@Mapper(uses = MyMapperUtil.class)
public interface TestMultiSourceMapper {

    TestMultiSourceMapper INSTANCE = Mappers.getMapper(TestMultiSourceMapper.class);

    /**
     * 多属性映射
     * @param loginRequest
     * @return
     */
    @Mappings({
            @Mapping(source = "loginRequest.loginType", target = "loginCode"),
            @Mapping(source = "multiSourceTestProperty", target = "multiSourceTestProperty")
    })
    LoginData multiSource(LoginRequest loginRequest, String multiSourceTestProperty);

}
