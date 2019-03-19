package io.mirko.requirement;

import io.mirko.requirement.validators.MustHaveInterests;
import io.mirko.requirement.validators.MustHaveLanguages;

import javax.validation.constraints.AssertTrue;
import java.util.*;
import java.util.stream.Collectors;

public class JobRequirement {
    @MustHaveLanguages(values={"en", "de"})
    final Set<String> languages = new HashSet<>();
    @AssertTrue
    volatile boolean willingToRelocate;
    @MustHaveInterests(values={InterestEnum.FUTURE_DEVELOPMENT_OF_HIGH_LEVEL_SPORT, InterestEnum.TECHNOLOGY})
    volatile EnumSet<InterestEnum> interests = EnumSet.noneOf(InterestEnum.class);
    @AssertTrue
    volatile boolean professionalJavaExperience;
    @AssertTrue
    volatile boolean computerScienceDegree;

    private JobRequirement () {}


    public static class Builder {
        private final JobRequirement jobRequirement = new JobRequirement();

        public Builder withLanguages(Locale ... locales) {
            jobRequirement.languages.addAll(Arrays.stream(locales).map(Locale::toString).collect(Collectors.toList()));
            return this;
        }

        public Builder willingToRelocate() {
            jobRequirement.willingToRelocate = true;
            return this;
        }

        public Builder notWillingToRelocate() {
            jobRequirement.willingToRelocate = false;
            return this;
        }

        public Builder withInterests(InterestEnum... interests) {
            jobRequirement.interests.addAll(Arrays.asList(interests));
            return this;
        }

        public Builder withProfessionalJavaExperience() {
            jobRequirement.professionalJavaExperience = true;
            return this;
        }

        public Builder withoutProfessionalJavaExperience() {
            jobRequirement.professionalJavaExperience = false;
            return this;
        }

        public Builder withComputerScienceDegree() {
            jobRequirement.computerScienceDegree = true;
            return this;
        }

        public Builder withoutComputerScienceDegree() {
            jobRequirement.computerScienceDegree = false;
            return this;
        }

        public JobRequirement build() {
            return jobRequirement;
        }
    }
}
