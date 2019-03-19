package io.mirko.requirement.validators;

import io.mirko.requirement.InterestEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;

public class MustHaveInterestsValidator implements ConstraintValidator<MustHaveInterests, Collection<InterestEnum>> {

    protected MustHaveInterests value;

    @Override
    public void initialize(MustHaveInterests value) {
        this.value = value;
    }

    @Override
    public boolean isValid(Collection<InterestEnum> interests, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(value.values()).allMatch(interests::contains);
    }
}
