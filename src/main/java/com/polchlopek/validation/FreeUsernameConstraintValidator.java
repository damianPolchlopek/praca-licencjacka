package com.polchlopek.validation;

import com.polchlopek.service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FreeUsernameConstraintValidator
        implements ConstraintValidator<FreeUsername ,String> {


    @Autowired
    private AplicationService applicationService;


    @Override
    public void initialize(FreeUsername freeUsername) {

    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext constraintValidatorContext) {

        return !applicationService.isUsername(username);
    }
}
