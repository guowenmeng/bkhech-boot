package com.bkhech.boot.configure.common.builder.dropdown;

import com.bkhech.boot.configure.common.builder.BaseBuilder;
import com.bkhech.boot.configure.common.builder.dropdown.response.DropDown;
import com.bkhech.boot.configure.common.builder.dropdown.response.DropDownResponse;

import java.util.List;

/**
 * Description: DropDownBuilder
 * @author guowm 2018/9/19
 */
public class DropDownBuilder implements BaseBuilder{

    private DropDownResponse response = new DropDownResponse();

    private DropDownBuilder(){}

    public DropDownBuilder data(List<DropDown> data) {
        this.response.setDropDownDtos(data);
        return this;
    }

    public DropDownResponse build() {
        return this.response;
    }

    public static DropDownBuilder newBuilder() {
        return new DropDownBuilder();
    }


}
