package com.example.realworldjdbc.validator;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64MinValidator implements ConstraintValidator<Base64Min, String> {

    private long min;

    @Override
    public void initialize(Base64Min constraintAnnotation) {
        this.min = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            return decodedBytes.length * 8 >= min;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
