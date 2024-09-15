package com.gub.validation;

import com.gub.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {

    /**
     *
     * @param value  需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        if(value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
