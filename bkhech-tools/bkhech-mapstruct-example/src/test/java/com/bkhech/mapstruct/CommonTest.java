package com.bkhech.mapstruct;

import com.bkhech.mapstruct.mapper.TestMapper;
import com.bkhech.mapstruct.mapper.TestMultiSourceMapper;
import com.bkhech.mapstruct.mapper.TestQualifiedByNameMapper;
import com.bkhech.mapstruct.mapper.TestUpdateMapper;
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
        assertThat(loginDatas.get(0).getUsername()).isEqualTo(username);
        assertThat(loginDatas.get(0).getLoginCode()).isEqualTo(loginType.name());
    }


    @Test
    public void testQualifiedByName() {
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
        LoginData loginData = TestQualifiedByNameMapper.INSTANCE.qualifiedByName(loginRequest);
        //then
        assertThat(loginData).isNotNull();
        assertThat(loginData.getUsername()).isEqualTo(username);
        assertThat(loginData.getLoginCode()).isEqualTo(loginType.name());

    }

    @Test
    public void testTestMultiSource() {
        String username = "guowm";
        LoginType loginType = LoginType.NORMAL;
        String testTestMulti = "testTestMulti";

        //given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("guowm");
        loginRequest.setPassword("1");
        loginRequest.setPhone("");
        loginRequest.setCode("");
        loginRequest.setLoginType(loginType);

        //when
        LoginData loginData = TestMultiSourceMapper.INSTANCE.multiSource(loginRequest, testTestMulti);
        //then
        assertThat(loginData).isNotNull();
        assertThat(loginData.getUsername()).isEqualTo(username);
        assertThat(loginData.getLoginCode()).isEqualTo(loginType.name());
        assertThat(loginData.getMultiSourceTestProperty()).isEqualTo(testTestMulti);

    }


    @Test
    public void testTestUpdate() {
        String username = "guowm";
        LoginType loginType = LoginType.NORMAL;
        String password = "1";

        //given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        loginRequest.setPhone("");
        loginRequest.setCode("");
        loginRequest.setLoginType(loginType);

        LoginData loginData = new LoginData();
        loginData.setUsername("1111");
        loginData.setPassword("2222");

        //when
        TestUpdateMapper.INSTANCE.update(loginRequest, loginData);
        //then
        assertThat(loginData.getUsername()).isEqualTo(username);
        assertThat(loginData.getPassword()).isEqualTo(password);

    }
}
