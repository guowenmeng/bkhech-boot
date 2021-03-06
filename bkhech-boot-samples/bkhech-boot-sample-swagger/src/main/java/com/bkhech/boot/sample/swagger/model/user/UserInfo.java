package com.bkhech.boot.sample.swagger.model.user;

import org.bkhech.mybatis.extend.generic.model.BasePrimaryKeyModel;

import java.io.Serializable;

/**
 * Model: UserInfo
 * Table: tb_userinfo
 * Alias: ui
 *
 * This Model generated by MyBatis Generator Extend.
 */
public class UserInfo extends BasePrimaryKeyModel<Integer> implements Serializable {
    /**
     * 
     * name
     */
    private String name;

    /**
     * 
     * age
     */
    private Integer age;

    private static final long serialVersionUID = 1L;

    /**
     * 
     * name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * name
     *
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * age
     *
     * @param age 
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" {");
        sb.append("id=").append(getId());
        sb.append(", name=").append(getName());
        sb.append(", age=").append(getAge());
        sb.append("}");
        return sb.toString();
    }
}