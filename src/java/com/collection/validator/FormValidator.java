package com.collection.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import com.collection.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class FormValidator implements Validator {
    
    private boolean empty;
    private boolean tooLong;
    private boolean duplicate;
    
    @Autowired
    @Qualifier("util")
    private Util util;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return SimpleList.class.equals(clazz) ||
               SimpleItem.class.equals(clazz) ||
                String.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        resetFlags();
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.SimpleList.name", "name must not be empty");
        //System.out.println(target.getClass().getName());
//        if (errors.getErrorCount() == 0) {
        if (target.getClass().getName().equals("com.collection.model.SimpleList")) {
            if (((SimpleList) target).getName().trim().length() > 30) {
                errors.rejectValue("name", "MaxLength.SimpleList.name");
                tooLong = true;
//                  System.out.println("length error SimpleList");
            }
            else if (((SimpleList) target).getName() == null || ((SimpleList) target).getName().trim().length() <= 0) {
                errors.rejectValue("name", "NotEmpty.SimpleList.name");
//                  System.out.println("length error SimpleList");
                empty = true;
            }
            for (SimpleList list : util.getLists().values()) {
                if (((SimpleList) target).getName().equals(list.getName())) {
                    System.out.println("duplicate found!");
                    errors.rejectValue("name", "Duplicate.SimpleList.name");
                    duplicate = true;
                }
            }
        }
        else if (target.getClass().getName().equals("com.collection.model.SimpleItem")) {
            System.out.println("SimpleItem");
            if (((SimpleItem) target).getName().trim().length() > 30) {
                errors.rejectValue("name", "MaxLength.SimpleItem.name");
                  System.out.println("length error SimpleItem");
                tooLong = true;
            }
            else if (((SimpleItem) target).getName() == null || ((SimpleItem) target).getName().trim().length() <= 0) {
                errors.rejectValue("name", "NotEmpty.SimpleItem.name");
                  System.out.println("length error SimpleItem");
                empty = true;
            }
        }
//        }
        System.out.println("error count = " + errors.getErrorCount());
    }
    
    public void updateModel(RedirectAttributes redirectAttributes) {
        if (empty) {
            redirectAttributes.addFlashAttribute("emptyField", "name must be specified");
        }
        if (tooLong) {
            redirectAttributes.addFlashAttribute("tooLong", "name must not be too long");
        }
        if (duplicate) {
            redirectAttributes.addFlashAttribute("duplicateField", "name must not already exist");
        }
    }
    private void resetFlags() {
        empty = false;
        tooLong = false;
        duplicate = false;
    }
}
