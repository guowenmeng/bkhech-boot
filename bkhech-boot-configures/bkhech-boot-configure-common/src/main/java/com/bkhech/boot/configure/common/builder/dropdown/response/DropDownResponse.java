package com.bkhech.boot.configure.common.builder.dropdown.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: DropDownResponse
 * @author guowm 2018/9/20
 */
public class DropDownResponse<T extends DropDown> extends ArrayList {

    public void setDropDownDtos(List<T> data){
        super.addAll(data);
    }


}
