package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers;

import io.github.edsongustavotofolo.microservicetemplate.domain.exceptions.BusinessRuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionControllerHandler {

    @ExceptionHandler(value = BusinessRuleException.class)
    public ResponseEntity<ErrorApi> businessRuleExceptionHander(BusinessRuleException ex, WebRequest request) {
        log.error("Falha de regra de negocio");
        var errorApi = ErrorApi.builder()
                .mensagens(List.of(ex.getMessage()))
                .dataHora(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Falha na validacao dos dados de requisicao");
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(fieldError -> fieldError.getDefaultMessage()).toList();
        var errorApi = ErrorApi.builder()
                .mensagens(errorMessages)
                .dataHora(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorApi> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Violacao de integridade no banco de dados");
        var errorApi = ErrorApi.builder()
                .mensagens(List.of(ex.getRootCause().getMessage()))
                .dataHora(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.internalServerError().body(errorApi);
    }
}
