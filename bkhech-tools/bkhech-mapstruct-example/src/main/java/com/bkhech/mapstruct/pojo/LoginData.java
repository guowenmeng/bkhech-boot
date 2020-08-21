package com.bkhech.mapstruct.pojo;

import lombok.Data;

/**
 * @author guowm
 * @date 2020/8/21
 * @description 
 */
@Data
public class LoginData {
    private String username;
    private String password;

    private String phone;
    private Integer code;

    private String loginCode;

    private String multiSourceTestProperty;

}