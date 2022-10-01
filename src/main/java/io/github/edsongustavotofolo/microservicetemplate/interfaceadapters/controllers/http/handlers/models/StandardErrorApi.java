package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class StandardErrorApi {
    private String path;
    private int status;
    private ZonedDateTime timestamp;
    private ErrorApiResponse error;
}
