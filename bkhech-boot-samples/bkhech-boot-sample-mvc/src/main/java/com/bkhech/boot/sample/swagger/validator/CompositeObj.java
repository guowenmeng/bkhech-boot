package com.bkhech.boot.sample.swagger.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description: CompositeObj
 * 自定义复杂对象参数校验，默认可为空
 * @author guowm 2018/9/25
 */
@Documented
@Constraint(validatedBy = {CompositeObjValidator.class}) //与注解相关的验证器
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(CompositeObj.List.class)
public @interface CompositeObj {

    String message() default "不能为null";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CompositeObj[] value();
    }

}
