package org.bkhech.mybatis.extend.generic.mapper;

import org.bkhech.mybatis.extend.generic.mapper.base.InsertMapper;
import org.bkhech.mybatis.extend.generic.mapper.base.SelectMapper;
import org.bkhech.mybatis.extend.generic.model.BaseModel;

/**
 * GenericWithoutPrimaryKeyMapper
 *
 * Created by guowm on 18-5-14.
 */
public interface GenericWithoutPrimaryKeyMapper<T extends BaseModel>
        extends InsertMapper<T>, SelectMapper<T> {

}
