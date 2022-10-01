package io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorApiResponse;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.StandardErrorApi;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.enums.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Locale;

@Getter
public class BusinessRuleException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorType errorType;

    public BusinessRuleException(final HttpStatus status, final ErrorType errorType) {
        this.status = status;
        this.errorType = errorType;
    }

    public StandardErrorApi getStandardErrorApi(final String path, final Locale locale) {
        return StandardErrorApi.builder()
                .path(path)
                .status(this.status.value())
                .timestamp(ZonedDateTime.now())
                .error(ErrorApiResponse.builder()
                        .code(this.errorType.name())
                        .message(this.errorType.getMessage(locale))
                        .build())
                .build();
    }
}
