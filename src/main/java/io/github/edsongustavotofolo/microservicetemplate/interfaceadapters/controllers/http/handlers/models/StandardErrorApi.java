package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorApiResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StandardErrorApi {
    private String path;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime timestamp;
    private ErrorApiResponse error;
}
