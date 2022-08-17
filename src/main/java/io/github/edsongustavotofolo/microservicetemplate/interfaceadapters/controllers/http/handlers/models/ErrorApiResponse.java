package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorApiResponse {
    private String code;
    private String message;
    private Map<String, List<String>> fields;
}
