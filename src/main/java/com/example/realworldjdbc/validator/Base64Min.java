package com.example.realworldjdbc.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.realworldjdbc.validator.Base64Min.List;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = Base64MinValidator.class)
@Repeatable(List.class)
public @interface Base64Min {

    String message() default "{validation.constraints.Base64Min.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long value();

    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Base64Min[] value();
    }
}
