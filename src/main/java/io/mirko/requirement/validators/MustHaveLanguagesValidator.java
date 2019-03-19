package io.mirko.requirement.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;

public class MustHaveLanguagesValidator implements ConstraintValidator<MustHaveLanguages, Collection<String>> {

    protected MustHaveLanguages value;

    @Override
    public void initialize(MustHaveLanguages value) {
        this.value = value;
    }

    @Override
    public boolean isValid(Collection<String> languages, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(value.values()).allMatch(languages::contains);
    }
}
