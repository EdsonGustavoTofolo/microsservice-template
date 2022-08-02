package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorApi {
    private int status;
    private LocalDateTime dataHora;
    private List<String> mensagens;
}
