package org.bkhech.mybatis.extend.generic.mapper.base;

import org.bkhech.mybatis.extend.generic.model.BaseModel;

/**
 * Created by guowm on 2017/11/2.
 */
public interface UpdateMapper<T extends BaseModel> {

    /**
     * 按ID更新数据
     * @param model
     * @return
     */
    int updateByPrimaryKey(T model);

    /**
     * 按ID更新数据，为空的属性不更新
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(T model);

}
