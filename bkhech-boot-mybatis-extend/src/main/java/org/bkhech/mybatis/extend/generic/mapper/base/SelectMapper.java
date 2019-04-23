package org.bkhech.mybatis.extend.generic.mapper.base;

import org.apache.ibatis.annotations.Param;
import org.bkhech.mybatis.extend.generic.model.BaseModel;
import org.bkhech.mybatis.extend.page.constant.PageConstant;
import org.bkhech.mybatis.extend.page.param.Page;

import java.util.List;

/**
 * Created by guowm on 2017/11/2.
 */
public interface SelectMapper<T extends BaseModel> {

    /**
     * 按model属性做等值匹配
     *
     * @param model
     * @return
     */
    List<T> select(@Param("model") T model);

    /**
     * 按model属性做等值匹配, 结果大于一条时会报错
     *
     * @param model
     * @return
     */
    T selectOne(@Param("model") T model);

    /**
     * 分页
     *
     * @param model
     * @param page
     * @return
     */
    List<T> selectPageList(@Param("model") T model, @Param(PageConstant.PAGE_KEY) Page page);

    /**
     * 数量
     *
     * @param model
     * @return
     */
    Integer selectCount(@Param("model") T model);

}
