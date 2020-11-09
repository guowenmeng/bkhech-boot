package com.bkhech.boot.commons.bean;

/**
 * @author guowm
 * @date 2019/7/4
 * 测试实体
 */
public class TestBean {

    private String idCard;
    private String name;
    private String sex;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "idCard=" + idCard +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
