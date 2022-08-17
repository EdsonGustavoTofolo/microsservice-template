package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.bundles.MessageSourceConfig;
import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.serialization.JacksonConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.ExceptionControllerHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

public abstract class ControllerTest {

    protected MockMvc mvc;
    protected ObjectMapper mapper;

    protected void setupBase(final Object controller) {
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionControllerHandler())
                .setLocaleResolver(new MessageSourceConfig().localeResolver(Locale.ENGLISH.getLanguage()))
                .build();
        this.mapper = new JacksonConfig().objectMapper();
    }

    protected String mapToJson(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
