package org.bkhech.mybatis.extend.generator.constant;

/**
 * Created by guowm on 2017/2/13.
 */
public interface GeneratorConstant {

    String BASE_MODEL_CLASS_PATH = "org.bkhech.mybatis.extend.generic.model.BaseModel";

    String BASE_PRIMARY_KEY_MODEL_CLASS_PATH = "org.bkhech.mybatis.extend.generic.model.BasePrimaryKeyModel";

    String GENERIC_MAPPER_CLASS_PATH = "org.bkhech.mybatis.extend.generic.mapper.GenericMapper";

    String GENERIC_WITHOUT_PRIMARY_KEY_MAPPER_CLASS_PATH = "org.bkhech.mybatis.extend.generic.mapper.GenericWithoutPrimaryKeyMapper";

    String GENERIC_SERVICE_CLASS_PATH = "org.bkhech.mybatis.extend.generic.service.GenericService";

    String GENERIC_WITHOUT_PRIMARY_KEY_SERVICE_CLASS_PATH = "org.bkhech.mybatis.extend.generic.service.GenericWithoutPrimaryKeyService";

    String GENERIC_SERVICE_IMPL_CLASS_PATH = "org.bkhech.mybatis.extend.generic.service.impl.GenericServiceImpl";

    String GENERIC_WITHOUT_PRIMARY_KEY_SERVICE_IMPL_CLASS_PATH = "org.bkhech.mybatis.extend.generic.service.impl.GenericWithoutPrimaryKeyServiceImpl";

    String AUTOWIRED_CLASS_PATH = "org.springframework.beans.factory.annotation.Autowired";

    String SERVICE_CLASS_PATH = "org.springframework.stereotype.Service";

    String TRANSACTIONAL_CLASS_PATH = "org.springframework.transaction.annotation.Transactional";

    String MAPPER_CLASS_PATH = "org.apache.ibatis.annotations.Mapper";

}
