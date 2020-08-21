package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 *     in Spring container
 */
@Mapper(componentModel = "spring", uses = MyMapperUtil.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    /**
     * 对象转换
     * @param loginRequest
     * @return
     */
    @Mapping(source = "loginType", target = "loginCode")
    LoginData loginRequestToLoginData(LoginRequest loginRequest);

    /**
     * 集合转换
     * 此时 loginRequestToLoginDatas 的实现为循环调用 loginRequestToLoginData 并继承了 loginRequestToLoginData 的属性映射
     * @param loginRequest
     * @return
     */
    List<LoginData> loginRequestToLoginDatas(List<LoginRequest> loginRequest);
}
