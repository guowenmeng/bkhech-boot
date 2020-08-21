package com.bkhech.mapstruct;

import com.bkhech.mapstruct.mapper.TestMapper;
import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import com.bkhech.mapstruct.pojo.LoginType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
public class CommonTest {

    @Test
    public void test() {
        String username = "guowm";
        LoginType loginType = LoginType.NORMAL;

        //given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("guowm");
        loginRequest.setPassword("1");
        loginRequest.setPhone("");
        loginRequest.setCode("");
        loginRequest.setLoginType(loginType);

        //when
        LoginData loginData = TestMapper.INSTANCE.loginRequestToLoginData(loginRequest);
        //then
        assertThat(loginData).isNotNull();
        assertThat(loginData.getUsername()).isEqualTo("guowm");
        assertThat(loginData.getLoginCode()).isEqualTo(loginType.name());

        List<LoginRequest> loginRequests = Arrays.asList(loginRequest);
        List<LoginData> loginDatas = TestMapper.INSTANCE.loginRequestToLoginDatas(loginRequests);

        assertThat(loginDatas).isNotNull();
        assertThat(loginDatas.size()).isNotEqualTo(0);
        assertThat(loginDatas.get(0).getUsername()).isEqualTo("guowm");
        assertThat(loginDatas.get(0).getLoginCode()).isEqualTo(loginType.name());
    }
}
