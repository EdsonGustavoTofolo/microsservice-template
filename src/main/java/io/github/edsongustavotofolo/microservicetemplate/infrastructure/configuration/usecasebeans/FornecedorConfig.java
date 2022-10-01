package io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.usecasebeans;

import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.AtualizarFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.CreateFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.impl.ContatoMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.impl.FornecedorMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorAtualizadoOutputBoundary;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreatedFornecedorOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FornecedorConfig {

    private final FornecedorDsGateway fornecedorDsGateway;
    private final CreatedFornecedorOutputPort fornecedorCriadoPresenter;
    private final FornecedorAtualizadoOutputBoundary fornecedorAtualizadoPresenter;

    @Bean
    public ContatoMapper contatoMapper() {
        return new ContatoMapperImpl();
    }

    @Bean
    public FornecedorMapper fornecedorMapper() {
        return new FornecedorMapperImpl(contatoMapper());
    }

    @Bean
    public CreateFornecedorInputPort criarFornecedorInputBoundary() {
        return new CreateFornecedorInteractor(fornecedorCriadoPresenter, fornecedorDsGateway, fornecedorMapper());
    }

    @Bean
    public AtualizarFornecedorInteractor atualizarFornecedorInteractor() {
        return new AtualizarFornecedorInteractor(fornecedorDsGateway, fornecedorAtualizadoPresenter, contatoMapper());
    }
}
