package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Users;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors
                ,"firstName","required.firstName","field firstName is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors  ,"lastName","required.lastName","field lastName is required");


    }
}
