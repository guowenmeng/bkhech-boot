package org.bkhech.mybatis.extend.generic.mapper.base;

import org.bkhech.mybatis.extend.generic.model.BaseModel;

import java.io.Serializable;

/**
 * Created by guowm on 2017/11/2.
 */
public interface SelectPrimaryKeyMapper<T extends BaseModel<PK>, PK extends Serializable> {

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

}
