package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.serialization.JacksonConfig;

public abstract class BaseControllerTest {

    private ObjectMapper mapper = new JacksonConfig().objectMapper();

//    protected void setupBase() {
//        this.mvc = MockMvcBuilders.standaloneSetup(controller)
//                .setControllerAdvice(new ExceptionControllerHandler())
//                .setLocaleResolver(new MessageSourceConfig().localeResolver(Locale.ENGLISH.getLanguage()))
//                .build();
//    }

    protected String mapToJson(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
