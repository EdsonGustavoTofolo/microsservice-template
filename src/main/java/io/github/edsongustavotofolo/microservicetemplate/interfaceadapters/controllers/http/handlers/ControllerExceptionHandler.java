package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorApiResponse;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.StandardErrorApi;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.BaseHttpException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = BaseHttpException.class)
    public ResponseEntity<StandardErrorApi> businessRuleExceptionHander(final BaseHttpException ex,
                                                                        final HttpServletRequest request) {
        log.error("Falha de regra de negocio", ex);

        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getStandardErrorApi(request.getRequestURI(), request.getLocale()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorApi> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException ex,
                                                                                   final HttpServletRequest request) {
        log.warn("Falha na validacao dos dados de requisicao");
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        var fields = fieldErrors.stream().collect(
                Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())
                ));
        var errorApi = StandardErrorApi.builder()
                .path(request.getRequestURI())
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ErrorApiResponse.builder()
                        .code(ErrorType.EXPT001.name())
                        .message(ErrorType.EXPT001.getMessage(request.getLocale()))
                        .fields(fields)
                        .build())
                .build();

        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<StandardErrorApi> dataIntegrityViolationExceptionHandler(final DataIntegrityViolationException ex,
                                                                                   final HttpServletRequest request) {
        log.error("Violacao de integridade no banco de dados", ex);
        var errorApi = StandardErrorApi.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ErrorApiResponse.builder()
                        .code(ErrorType.EXPT002.name())
                        .message(ErrorType.EXPT002.getMessage(request.getLocale()))
                        .build())
                .build();
        return ResponseEntity.internalServerError().body(errorApi);
    }
}
