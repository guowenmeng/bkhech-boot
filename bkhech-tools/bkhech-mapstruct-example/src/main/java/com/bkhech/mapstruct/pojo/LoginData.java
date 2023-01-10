package com.bkhech.mapstruct.pojo;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
public class LoginData {
    private String username;
    private String password;

    private String phone;
    private Integer code;

    private String loginCode;

    private String multiSourceTestProperty;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getMultiSourceTestProperty() {
        return multiSourceTestProperty;
    }

    public void setMultiSourceTestProperty(String multiSourceTestProperty) {
        this.multiSourceTestProperty = multiSourceTestProperty;
    }
}