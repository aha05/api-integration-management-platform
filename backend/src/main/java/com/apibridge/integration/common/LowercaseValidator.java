package com.apibridge.integration.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<Lowercase, String> {  // ConstraintValidator is generic interface with two generic type parameters, the first parameter is our custom annotation, the second parameter represents the type of data we want to appy our annotation on. there is two method two implement here but we need the isValid implementation only.
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true; // since strings are nullable the lowercase doesn't applied to null object.
        return value.equals(value.toLowerCase()); // if this condition is true the value is lowercase.
    }
}
