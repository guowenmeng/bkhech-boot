package com.bkhech.boot.sample.swagger.validator;

import com.bkhech.boot.sample.swagger.dto.CompositeParam;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description: CompositeObjValidator
 * @author guowm 2018/9/25
 */
public class CompositeObjValidator implements ConstraintValidator<CompositeObj, CompositeParam> {
    @Override
    public void initialize(CompositeObj constraintAnnotation) {
    }

    @Override
    public boolean isValid(CompositeParam value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("the property of ");
        boolean flag = false;
        if (value.getComposite() == null) {
            sb.append("composite,");
            flag = true;
        }
        if (value.getSex() == null) {
            sb.append("sex,");
            flag = true;
        }

        if (flag) {
            sb.insert(sb.lastIndexOf(","), " from compositeParam is null");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(sb.toString())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
