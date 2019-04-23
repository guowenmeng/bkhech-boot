package org.bkhech.mybatis.extend.generic.mapper.base;

import org.bkhech.mybatis.extend.generic.model.BaseModel;

import java.util.List;

/**
 * Created by guowm on 2017/11/2.
 */
public interface InsertMapper<T extends BaseModel> {

    /**
     * 插入数据
     *
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 插入数据，为空的属性不插入
     *
     * @param model
     * @return
     */
    int insertSelective(T model);

    /**
     * 批量插入数据
     *
     * @return
     */
    int batchInsert(List<T> list);

}
