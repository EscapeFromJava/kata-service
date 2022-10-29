package com.example.authservice.models.validation;

import com.example.authservice.models.enums.RoleNameEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CheckRoleValidator implements ConstraintValidator<CheckRole, String> {

    @Override
    public boolean isValid(String enteredRole, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(RoleNameEnum.values()).anyMatch(r -> r.name().equals(enteredRole));
    }
}
