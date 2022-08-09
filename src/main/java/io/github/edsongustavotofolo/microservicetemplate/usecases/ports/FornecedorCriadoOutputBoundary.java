package io.github.edsongustavotofolo.microservicetemplate.usecases.ports;

import io.github.edsongustavotofolo.microservicetemplate.domain.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

public interface FornecedorCriadoOutputBoundary {
    NovoFornecedorResponseModel present(final Integer id);

    void throwValidationError(final String mensagem) throws BusinessRuleException;
}
