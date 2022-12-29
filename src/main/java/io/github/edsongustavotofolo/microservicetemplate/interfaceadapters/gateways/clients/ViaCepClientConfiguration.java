package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class ViaCepClientConfiguration {

    @Bean
    Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
}
