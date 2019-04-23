package org.bkhech.mybatis.extend.generic.mapper;

import org.bkhech.mybatis.extend.generic.model.BaseModel;
import org.bkhech.mybatis.extend.generic.mapper.base.InsertMapper;
import org.bkhech.mybatis.extend.generic.mapper.base.DeleteMapper;
import org.bkhech.mybatis.extend.generic.mapper.base.UpdateMapper;
import org.bkhech.mybatis.extend.generic.mapper.base.SelectMapper;
import org.bkhech.mybatis.extend.generic.mapper.base.SelectPrimaryKeyMapper;

import java.io.Serializable;

/**
 * GenericMapper
 *
 * Created by guowm on 2017/2/10.
 */
public interface GenericMapper<T extends BaseModel<PK>, PK extends Serializable>
        extends InsertMapper<T>, DeleteMapper<PK>, UpdateMapper<T>, SelectMapper<T>, SelectPrimaryKeyMapper<T, PK> {

}
