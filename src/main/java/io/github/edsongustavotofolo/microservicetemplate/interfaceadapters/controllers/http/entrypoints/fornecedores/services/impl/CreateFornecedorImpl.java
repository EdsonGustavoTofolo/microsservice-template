package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.mappers.CreateFornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.services.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFornecedorImpl implements CreateFornecedor {

    private final CreateFornecedorInputPort createFornecedor;
    private final CreateFornecedorOutputPort<CreatedFornecedor> createdFornecedor;

    @Override
    public Integer execute(final CreateFornecedorRequest request) throws BusinessRuleException {
        final var model = CreateFornecedorMapper.toModel(request);

        this.createFornecedor.execute(model);

        final var created = this.createdFornecedor.get();

        return created.getId();
    }
}