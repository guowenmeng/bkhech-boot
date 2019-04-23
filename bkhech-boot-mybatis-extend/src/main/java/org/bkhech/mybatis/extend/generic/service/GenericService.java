package org.bkhech.mybatis.extend.generic.service;

import org.bkhech.mybatis.extend.generic.mapper.GenericMapper;
import org.bkhech.mybatis.extend.generic.model.BaseModel;

import java.io.Serializable;

/**
 * GenericService
 *
 * Created by guowm on 2017/2/13.
 */
public interface GenericService<T extends BaseModel<PK>, PK extends Serializable> extends GenericMapper<T, PK> {

}
