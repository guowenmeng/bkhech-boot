package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 *     in Spring container
 */
@Mapper(componentModel = "spring", uses = MyStringIntegerMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    /**
     * loginRequest to loginData
     * @param loginRequest
     * @return
     */
    LoginData loginRequestToLoginData(LoginRequest loginRequest);
}
