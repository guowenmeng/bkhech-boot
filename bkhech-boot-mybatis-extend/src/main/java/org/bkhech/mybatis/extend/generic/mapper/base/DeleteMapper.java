package org.bkhech.mybatis.extend.generic.mapper.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by guowm on 2017/11/2.
 */
public interface DeleteMapper<PK extends Serializable> {

    /**
     * 按主键删除数据，物理删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 批量删除数据
     * @param list
     * @return
     */
    int deleteByIds(List<PK> list);
}
