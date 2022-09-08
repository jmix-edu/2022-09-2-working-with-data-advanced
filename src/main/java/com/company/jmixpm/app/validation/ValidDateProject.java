package com.company.jmixpm.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidDateProjectValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDateProject {

    String message() default "Start date cannot be later than end date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
