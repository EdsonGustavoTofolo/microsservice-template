package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.annotations.validators;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.annotations.ExistsCountry;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsCountryValidator implements ConstraintValidator<ExistsCountry, Integer> {

    @Autowired
    private CidadeProvider cidadeProvider;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return this.cidadeProvider.existsById(integer);
    }
}
