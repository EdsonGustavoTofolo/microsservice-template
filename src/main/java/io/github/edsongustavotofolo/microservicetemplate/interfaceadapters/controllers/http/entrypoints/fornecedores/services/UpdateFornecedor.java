package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.services;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface UpdateFornecedor {
    void execute(final Integer id, final UpdateFornecedorRequest updateFornecedorRequest) throws BusinessRuleException;
}
