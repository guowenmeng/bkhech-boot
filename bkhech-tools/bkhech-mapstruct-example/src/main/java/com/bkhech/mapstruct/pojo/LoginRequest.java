package com.bkhech.mapstruct.pojo;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
public class LoginRequest {

    private String username;
    private String password;

    private String phone;
    private String code;

    /**
     * @see LoginType
     */
    private LoginType loginType;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
