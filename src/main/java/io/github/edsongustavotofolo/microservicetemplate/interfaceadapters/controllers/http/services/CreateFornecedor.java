package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.services;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;

public interface CreateFornecedor {
    Integer execute(final CreateFornecedorRequest request) throws BusinessRuleException;
}
