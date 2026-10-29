package com.apibridge.integration.integration.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LowercaseValidator.class) // we need also link this annotation interface with its implementation class(validator class)
public @interface Lowercase {
    String message() default "must be lowercase";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
