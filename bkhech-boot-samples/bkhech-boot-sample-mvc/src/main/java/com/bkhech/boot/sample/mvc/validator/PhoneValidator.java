package com.bkhech.boot.sample.mvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description: PhoneValidator
 * @author guowm 2018/9/25
 */
public class PhoneValidator implements ConstraintValidator<Phone, String>{

    private String regexp;

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.matches(regexp)) {
            return true;
        }
        return false;
    }
}
