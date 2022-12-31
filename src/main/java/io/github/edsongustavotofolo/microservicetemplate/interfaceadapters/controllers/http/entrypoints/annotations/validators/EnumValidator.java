package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.validators;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<Enum, String> {

    private List<String> validValues;

    @Override
    public void initialize(final Enum constraintAnnotation) {
        this.validValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(java.lang.Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || this.validValues.contains(value);
    }
}
