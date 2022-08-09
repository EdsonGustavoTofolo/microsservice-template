package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ControllerTest {

    protected final MockMvc mvc;
    protected final ObjectMapper mapper;

    public ControllerTest(MockMvc mvc, ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    protected String mapToJson(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
