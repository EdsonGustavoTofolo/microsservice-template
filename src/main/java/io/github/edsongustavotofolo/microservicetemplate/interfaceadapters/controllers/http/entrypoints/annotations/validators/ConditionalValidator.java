package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.validators;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Conditional;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import static org.apache.tomcat.util.IntrospectionUtils.getProperty;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {

    private String selected;
    private String[] required;
    private String message;
    private String[] values;

    @Override
    public void initialize(Conditional constraintAnnotation) {
        this.selected = constraintAnnotation.selected();
        this.required = constraintAnnotation.required();
        this.message = constraintAnnotation.message();
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean isInvalid = false;

        String actualValue = String.valueOf(getProperty(object, this.selected));
        if (Arrays.asList(this.values).contains(actualValue)) {
            for (String propName : this.required) {
                Object requiredValue = getProperty(object, propName);
                boolean valid = requiredValue != null && StringUtils.isNotBlank((CharSequence) requiredValue);
                if (!valid) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(this.message).addPropertyNode(propName).addConstraintViolation();
                }
                isInvalid |= !valid;
            }
        }

        return !isInvalid;
    }
}
