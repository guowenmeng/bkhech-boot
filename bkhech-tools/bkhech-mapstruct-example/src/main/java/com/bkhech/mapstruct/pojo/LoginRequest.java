package com.bkhech.mapstruct.pojo;

import lombok.Data;

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
    private LoginType loginType;

}
