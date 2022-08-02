package io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.usecasebeans;

import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.CriarFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.impl.FornecedorMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CriarFornecedorInputBoundary;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FornecedorConfig {

    private final FornecedorDsGateway fornecedorDsGateway;
    private final FornecedorCriadoOutputBoundary presenter;

    @Bean
    public FornecedorMapper fornecedorMapper() {
        return new FornecedorMapperImpl();
    }

    @Bean
    public CriarFornecedorInputBoundary criarFornecedorInputBoundary() {
        return new CriarFornecedorInteractor(
                presenter, fornecedorDsGateway, fornecedorMapper());
    }
}
