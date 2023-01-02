package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.DeleteFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.DeleteFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFornecedorImpl implements DeleteFornecedor {

    private final DeleteFornecedorInputPort deleteFornecedor;

    @Override
    public void execute(final Integer id) throws BusinessRuleException {
        this.deleteFornecedor.execute(id);
    }
}
