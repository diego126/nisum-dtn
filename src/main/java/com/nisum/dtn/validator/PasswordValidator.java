package com.nisum.dtn.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<CustomRegex, String> {

    @Value("${password.validation.regex}")
    private String passwordRegex;

    @Override
    public void initialize(CustomRegex constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(value).matches();
    }
}
