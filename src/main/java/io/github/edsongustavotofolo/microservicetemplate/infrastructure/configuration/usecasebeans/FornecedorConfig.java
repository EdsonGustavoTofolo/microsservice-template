package io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.usecasebeans;

import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.AtualizarFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.CreateFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl.ContatoMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl.FornecedorMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.FornecedorAtualizadoOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreatedFornecedorOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FornecedorConfig {

    private final FornecedorProvider fornecedorProvider;
    private final CreatedFornecedorOutputPort fornecedorCriadoPresenter;
    private final FornecedorAtualizadoOutputPort fornecedorAtualizadoPresenter;

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
        return new CreateFornecedorInteractor(fornecedorCriadoPresenter, fornecedorProvider, fornecedorMapper());
    }

    @Bean
    public AtualizarFornecedorInteractor atualizarFornecedorInteractor() {
        return new AtualizarFornecedorInteractor(fornecedorProvider, fornecedorAtualizadoPresenter, contatoMapper());
    }
}
