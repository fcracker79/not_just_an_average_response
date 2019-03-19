package io.mirko;

import io.mirko.requirement.InterestEnum;
import io.mirko.requirement.JobRequirement;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Locale;

public class Main {
    public static void main(String ... args) throws Exception {
        final JobRequirement jobRequirement = new JobRequirement.Builder()
                .withInterests(InterestEnum.TECHNOLOGY, InterestEnum.MUSIC)
                .withLanguages(Locale.ITALIAN, Locale.ENGLISH)
                .withProfessionalJavaExperience()
                .withComputerScienceDegree()
                .notWillingToRelocate()
                .build();
        final Disposable d = Flowable.just(jobRequirement)
                .map(j -> Validation.buildDefaultValidatorFactory().getValidator().validate(j))
                .subscribe(
                        validations -> {
                            if (validations.isEmpty()) {
                                System.out.println("Please download my CV at www.mirko.io");
                            } else {
                                System.out.println("Sorry I cannot apply because:");
                                for (ConstraintViolation v : validations) {
                                    System.out.format("%s: %s\n", v.getPropertyPath(), v.getMessage());
                                }
                            }
                        }
                );
        while (!d.isDisposed()) {
            Thread.sleep(3000);
        }
    }
}
