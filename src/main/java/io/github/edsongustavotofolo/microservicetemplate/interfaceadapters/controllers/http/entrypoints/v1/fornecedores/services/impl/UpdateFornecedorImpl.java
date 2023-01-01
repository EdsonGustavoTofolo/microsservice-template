package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.mappers.UpdateFornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.UpdateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateFornecedorImpl implements UpdateFornecedor {

    private final UpdateFornecedorInputPort updateFornecedor;

    @Override
    public void execute(final Integer id, final UpdateFornecedorRequest updateFornecedorRequest) throws BusinessRuleException {
        final var model = UpdateFornecedorMapper.INSTANCE.toModel(id, updateFornecedorRequest);
        this.updateFornecedor.execute(model);
    }
}
