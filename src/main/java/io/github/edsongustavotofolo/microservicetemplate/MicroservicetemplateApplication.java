package io.github.edsongustavotofolo.microservicetemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(info = @Info(
        title = "Fornecedores CRUD",
        version = "${application.version}",
        description = "API for manage fornecedores"))
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
