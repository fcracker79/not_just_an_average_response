package io.mirko.requirement.validators;

import io.mirko.requirement.InterestEnum;
import io.mirko.requirement.validators.MustHaveInterests;
import io.mirko.requirement.validators.MustHaveLanguages;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

public class TestValidators {
    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testMustHaveInterests() {
        Assert.assertEquals(
                0,
                validator.validate(
                        new BeanMustHaveInterests(
                                InterestEnum.FUTURE_DEVELOPMENT_OF_HIGH_LEVEL_SPORT,
                                InterestEnum.TECHNOLOGY
                        )
                ).size()
        );
        Assert.assertEquals(
                0,
                validator.validate(
                        new BeanMustHaveInterests(
                                InterestEnum.FUTURE_DEVELOPMENT_OF_HIGH_LEVEL_SPORT,
                                InterestEnum.TECHNOLOGY,
                                InterestEnum.MUSIC
                        )
                ).size()
        );
        Assert.assertEquals(
                1,
                validator.validate(
                        new BeanMustHaveInterests(
                                InterestEnum.FUTURE_DEVELOPMENT_OF_HIGH_LEVEL_SPORT
                        )
                ).size()
        );
        Assert.assertEquals(
                1,
                validator.validate(new BeanMustHaveInterests()).size()
        );
    }

    @Test
    public void testMustHaveLanguages() {
        Assert.assertEquals(0, validator.validate(new BeanMustHaveLanguages(Locale.ENGLISH, Locale.GERMAN)).size());
        Assert.assertEquals(0, validator.validate(new BeanMustHaveLanguages(Locale.ENGLISH, Locale.GERMAN, Locale.ITALIAN)).size());
        Assert.assertEquals(1, validator.validate(new BeanMustHaveLanguages(Locale.ENGLISH, Locale.ITALIAN)).size());
        Assert.assertEquals(1, validator.validate(new BeanMustHaveLanguages()).size());

    }

    public static class BeanMustHaveInterests {
        @MustHaveInterests(values={InterestEnum.FUTURE_DEVELOPMENT_OF_HIGH_LEVEL_SPORT, InterestEnum.TECHNOLOGY})
        private final EnumSet<InterestEnum> interests = EnumSet.noneOf(InterestEnum.class);

        public BeanMustHaveInterests(InterestEnum ... interests) {
            this.interests.addAll(Arrays.asList(interests));
        }
    }

    public static class BeanMustHaveLanguages {
        @MustHaveLanguages(values={"en", "de"})
        private final Set<String> languages = new HashSet<>();

        public BeanMustHaveLanguages(Locale ... languages) {
            this.languages.addAll(Arrays.stream(languages).map(Locale::toString).collect(Collectors.toList()));
        }
    }
}
