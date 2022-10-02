package io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.usecasebeans;

import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.AtualizarFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.CreateFornecedorInteractor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.FornecedorAtualizadoOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FornecedorHttpConfig {

    private final FornecedorProvider fornecedorProvider;
    private final CreateFornecedorOutputPort<CreatedFornecedor> fornecedorCriadoPresenter;
    private final FornecedorAtualizadoOutputPort fornecedorAtualizadoPresenter;
    private final FornecedorMapper fornecedorMapper;
    private final ContatoMapper contatoMapper;

    @Bean
    public CreateFornecedorInputPort<CreatedFornecedor> criarFornecedorHttp() {
        return new CreateFornecedorInteractor<>(this.fornecedorCriadoPresenter,
                this.fornecedorProvider, this.fornecedorMapper);
    }

    @Bean
    public AtualizarFornecedorInteractor atualizarFornecedorHttp() {
        return new AtualizarFornecedorInteractor(this.fornecedorProvider,
                this.fornecedorAtualizadoPresenter, this.contatoMapper);
    }
}
