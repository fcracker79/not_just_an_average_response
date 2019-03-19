package io.mirko.requirement.validators;


import io.mirko.requirement.InterestEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Locale;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MustHaveLanguagesValidator.class)
@Documented
public @interface MustHaveLanguages {
    String message() default "{io.mirko.validation.constraints.MustHaveLanguages.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String[] values();
}
