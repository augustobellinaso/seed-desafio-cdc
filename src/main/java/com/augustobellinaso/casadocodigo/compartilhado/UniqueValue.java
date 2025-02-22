package com.augustobellinaso.casadocodigo.compartilhado;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueValue {

    String message() default "{NotBlank}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();
}
