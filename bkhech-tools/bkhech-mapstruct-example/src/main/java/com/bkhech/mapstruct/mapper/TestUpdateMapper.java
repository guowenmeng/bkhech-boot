package com.bkhech.mapstruct.mapper;

import com.bkhech.mapstruct.pojo.LoginData;
import com.bkhech.mapstruct.pojo.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author guowm
 * @date 2020/8/21
 * @description
 */
@Mapper(uses = MyMapperUtil.class)
public interface TestUpdateMapper {

    TestUpdateMapper INSTANCE = Mappers.getMapper(TestUpdateMapper.class);

    /**
     * 更新对象
     *      @MappingTarget标注的类LoginData 为目标类，LoginRequest类为源类，调用此方法，会把源类中的属性更新到目标类中。
     *      更新规则还是由@Mapping指定。
     * @param loginRequest
     * @return
     */
    @Mapping(source = "loginType", target = "loginCode")
    void update(LoginRequest loginRequest, @MappingTarget LoginData loginData);

}
