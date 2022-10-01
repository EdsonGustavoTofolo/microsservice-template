package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorApiResponse;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.StandardErrorApi;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Locale;

@Getter
public class BaseHttpException extends BusinessRuleException {

    private final HttpStatus status;

    public BaseHttpException(final HttpStatus status, final ErrorType errorType, final Throwable cause) {
        super(errorType, cause);
        this.status = status;
    }

    public BaseHttpException(final HttpStatus status, final ErrorType errorType) {
        super(errorType);
        this.status = status;
    }

    public StandardErrorApi getStandardErrorApi(final String path, final Locale locale) {
        return StandardErrorApi.builder()
                .path(path)
                .status(this.status.value())
                .timestamp(ZonedDateTime.now())
                .error(ErrorApiResponse.builder()
                        .code(getErrorType().name())
                        .message(getErrorType().getMessage(locale))
                        .build())
                .build();
    }
}
