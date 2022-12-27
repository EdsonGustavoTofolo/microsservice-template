package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Value
public class ErrorField {
    String field;
    List<String> messages;

    public ErrorField(final String key, final List<String> value) {
        this.field = key;
        this.messages = value;
    }
}
