package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorNotFoundException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorAtualizadoOutputBoundary;
import org.springframework.stereotype.Service;

@Service
public class FornecedorAtualizadoPresenter implements FornecedorAtualizadoOutputBoundary {

    @Override
    public Fornecedor fornecedorNaoEncontrado() throws BusinessRuleException {
        throw new FornecedorNotFoundException();
    }
}
