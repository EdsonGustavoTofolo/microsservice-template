package io.github.edsongustavotofolo.microservicetemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(info = @Info(
        title = "${application.name}",
        version = "${application.version}",
        description = "${application.description}"))
@SecurityScheme(name = "bearerToken",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Bearer token provided by keycloak")
@SpringBootApplication(scanBasePackages = {
        "io.github.edsongustavotofolo.microservicetemplate.infrastructure",
        "io.github.edsongustavotofolo.microservicetemplate.interfaceadapters",
        "io.github.edsongustavotofolo.microservicetemplate.usecases",
})
@EntityScan("io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model")
@EnableJpaRepositories(
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class,
        basePackages = "io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository")
@EnableFeignClients(basePackages = "io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients")
public class MicroservicetemplateApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MicroservicetemplateApplication.class, args);
    }

}
