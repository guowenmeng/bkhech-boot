package org.bkhech.mybatis.extend.generic.service;

import org.bkhech.mybatis.extend.generic.mapper.GenericWithoutPrimaryKeyMapper;
import org.bkhech.mybatis.extend.generic.model.BaseModel;

/**
 * GenericWithoutPrimaryKeyService
 *
 * Created by guowm on 18-5-14.
 */
public interface GenericWithoutPrimaryKeyService<T extends BaseModel> extends GenericWithoutPrimaryKeyMapper<T> {
    
}
