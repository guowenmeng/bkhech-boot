package com.bkhech.mapstruct.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
@Data
public class LoginRequest {

    private String username;
    private String password;

    private String phone;
    private String code;

    /**
     * @see LoginType
     */
    @NotNull(message = "登陆类型不能为空")
    private LoginType loginType;

}
