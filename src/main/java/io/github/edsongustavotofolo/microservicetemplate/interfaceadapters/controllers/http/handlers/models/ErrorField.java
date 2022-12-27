package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorField {
    String field;
    List<String> messages;

    public ErrorField(final String key, final List<String> value) {
        this.field = key;
        this.messages = value;
    }
}
