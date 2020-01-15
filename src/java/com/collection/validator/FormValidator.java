package com.collection.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;

@Component
public class FormValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return SimpleList.class.equals(clazz) || SimpleItem.class.equals(clazz)|| String.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name must not be empty");
    }
}
