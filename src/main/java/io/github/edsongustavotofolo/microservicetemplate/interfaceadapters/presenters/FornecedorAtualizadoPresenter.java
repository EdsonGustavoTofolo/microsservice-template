package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorNotFoundException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.FornecedorAtualizadoOutputPort;
import org.springframework.stereotype.Service;

@Service
public class FornecedorAtualizadoPresenter implements FornecedorAtualizadoOutputPort {

    @Override
    public Fornecedor fornecedorNaoEncontrado() throws BusinessRuleException {
        throw new FornecedorNotFoundException();
    }
}
