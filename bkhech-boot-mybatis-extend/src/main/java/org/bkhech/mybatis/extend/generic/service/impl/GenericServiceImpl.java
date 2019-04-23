package org.bkhech.mybatis.extend.generic.service.impl;

import org.bkhech.mybatis.extend.generic.service.GenericService;
import org.bkhech.mybatis.extend.generic.mapper.GenericMapper;
import org.bkhech.mybatis.extend.generic.model.BaseModel;
import org.bkhech.mybatis.extend.page.param.Page;

import java.io.Serializable;
import java.util.List;

/**
 * GenericServiceImpl
 *
 * Created by guowm on 2017/2/13.
 */
public abstract class GenericServiceImpl<T extends BaseModel<PK>, PK extends Serializable, M extends GenericMapper<T, PK>>
        implements GenericService<T, PK> {

    public abstract M getGenericMapper();

    @Override
    public int deleteByPrimaryKey(PK id) {
        return getGenericMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T model) {
        return getGenericMapper().insert(model);
    }

    @Override
    public int insertSelective(T model) {
        return getGenericMapper().insertSelective(model);
    }

    @Override
    public T selectByPrimaryKey(PK id) {
        return getGenericMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(T model) {
        return getGenericMapper().updateByPrimaryKey(model);
    }

    @Override
    public int updateByPrimaryKeySelective(T model) {
        return getGenericMapper().updateByPrimaryKeySelective(model);
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

    @Override
    public int batchInsert(List<T> list) {
        return getGenericMapper().batchInsert(list);
    }

    @Override
    public int deleteByIds(List<PK> list) {
        return getGenericMapper().deleteByIds(list);
    }
}
