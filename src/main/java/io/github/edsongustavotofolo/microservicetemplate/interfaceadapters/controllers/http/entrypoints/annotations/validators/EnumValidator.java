package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.validators;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Enum;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<Enum, String> {

    private List<String> validValues;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.validValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(java.lang.Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(value) || this.validValues.contains(value);
    }
}
