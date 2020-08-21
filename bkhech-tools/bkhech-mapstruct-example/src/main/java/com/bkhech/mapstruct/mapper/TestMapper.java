package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 * for resolve Exception when code is ""
 *     java.lang.NumberFormatException: For input string: ""
 */
@Mapper(uses = MyStringIntegerMapper.class)
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

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
