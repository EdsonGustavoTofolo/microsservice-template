package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

public interface FornecedorCriadoOutputBoundary {
    NovoFornecedorResponseModel present(final Integer id);
    void cnpjInvalido() throws BusinessRuleException;
    void fornecedorJaExisteComCnpj() throws BusinessRuleException;
}
