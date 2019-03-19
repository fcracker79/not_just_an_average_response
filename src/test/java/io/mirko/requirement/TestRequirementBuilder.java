package io.mirko.requirement;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;

public class TestRequirementBuilder {
    @Test
    public void test() {
        Assert.assertFalse(new JobRequirement.Builder().notWillingToRelocate().build().willingToRelocate);
        Assert.assertTrue(new JobRequirement.Builder().willingToRelocate().build().willingToRelocate);

        Assert.assertEquals(
                new HashSet<>(Arrays.asList(Locale.ITALIAN.toString(), Locale.FRENCH.toString())),
                new JobRequirement.Builder().withLanguages(Locale.ITALIAN, Locale.FRENCH).build().languages);

        Assert.assertEquals(
                EnumSet.of(InterestEnum.TECHNOLOGY, InterestEnum.MUSIC),
                new JobRequirement.Builder().withInterests(InterestEnum.TECHNOLOGY, InterestEnum.MUSIC)
                .build().interests
        );

        Assert.assertFalse(new JobRequirement.Builder().withoutProfessionalJavaExperience().build().professionalJavaExperience);
        Assert.assertTrue(new JobRequirement.Builder().withProfessionalJavaExperience().build().professionalJavaExperience);

        Assert.assertFalse(new JobRequirement.Builder().withoutComputerScienceDegree().build().computerScienceDegree);
        Assert.assertTrue(new JobRequirement.Builder().withComputerScienceDegree().build().computerScienceDegree);
    }
}
