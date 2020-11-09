package com.bkhech.boot.sample.swagger.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Description: CompositeParam
 * @author guowm 2018/9/25
 */
public class CompositeParam2 {

    @NotEmpty(message = "composite不能为空")
    private String composite ;
    @NotBlank(message = "sex不能为空")
    private String sex ;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getComposite() {
        return composite;
    }

    public void setComposite(String composite) {
        this.composite = composite;
    }
}
