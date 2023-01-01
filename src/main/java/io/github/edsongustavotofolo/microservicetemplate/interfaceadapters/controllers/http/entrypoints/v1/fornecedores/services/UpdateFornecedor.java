package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface UpdateFornecedor {
    void execute(final Integer id, final UpdateFornecedorRequest updateFornecedorRequest) throws BusinessRuleException;
}
