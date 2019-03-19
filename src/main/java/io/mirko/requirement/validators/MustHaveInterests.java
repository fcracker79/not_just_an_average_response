package io.mirko.requirement.validators;


import io.mirko.requirement.InterestEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MustHaveInterestsValidator.class)
@Documented
public @interface MustHaveInterests {
    String message() default "{io.mirko.validation.constraints.MustHaveInterests.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    InterestEnum[] values();
}
