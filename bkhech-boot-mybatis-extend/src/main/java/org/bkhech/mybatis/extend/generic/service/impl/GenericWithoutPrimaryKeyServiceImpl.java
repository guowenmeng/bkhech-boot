package org.bkhech.mybatis.extend.generic.service.impl;

import org.bkhech.mybatis.extend.generic.mapper.GenericWithoutPrimaryKeyMapper;
import org.bkhech.mybatis.extend.generic.model.BaseModel;
import org.bkhech.mybatis.extend.generic.service.GenericWithoutPrimaryKeyService;
import org.bkhech.mybatis.extend.page.param.Page;

import java.util.List;

/**
 * GenericWithoutPrimaryKeyServiceImpl
 *
 * Created by guowm on 18-5-14.
 */
public abstract class GenericWithoutPrimaryKeyServiceImpl<T extends BaseModel, M extends GenericWithoutPrimaryKeyMapper<T>> implements GenericWithoutPrimaryKeyService<T> {

    public abstract M getGenericMapper();

    @Override
    public int insert(T model) {
        return getGenericMapper().insert(model);
    }

    @Override
    public int insertSelective(T model) {
        return getGenericMapper().insertSelective(model);
    }

    @Override
    public int batchInsert(List<T> list) {
        return getGenericMapper().batchInsert(list);
    }

    @Override
    public List<T> select(T model) {
        return getGenericMapper().select(model);
    }

    @Override
    public T selectOne(T model) {
        return getGenericMapper().selectOne(model);
    }

    @Override
    public List<T> selectPageList(T model, Page page) {
        return getGenericMapper().selectPageList(model, page);
    }

    @Override
    public Integer selectCount(T model) {
        return getGenericMapper().selectCount(model);
    }
}
