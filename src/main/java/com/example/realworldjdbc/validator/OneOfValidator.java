package com.example.realworldjdbc.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.ReflectionUtils;

public class OneOfValidator implements ConstraintValidator<OneOf, Object> {

    private String[] properties;

    @Override
    public void initialize(OneOf constraintAnnotation) {
        this.properties = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Class<?> clazz = value.getClass();

        List<Object> list = Arrays.stream(properties)
            .map(property -> Optional.ofNullable(ReflectionUtils.findField(clazz, property)))
            .map(field -> field.map(f -> {
                f.setAccessible(true);
                return ReflectionUtils.getField(f, value);
            }))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

        return list.size() == 1;
    }
}
