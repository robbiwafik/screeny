package com.company.screeny.validation.annotation;

import com.company.screeny.validation.constraint.ReferencePKConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ReferencePKConstraintValidator.class)
public @interface ValidGenreId {
      String message() default "genreId field should be valid";

      Class<?>[] groups() default {};

      Class<? extends Payload>[] payload() default {};
}
