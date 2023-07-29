package com.nisum.dtn.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface CustomRegex {
    String message() default "Password must contain letters and numbers and must be longer than 3 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
