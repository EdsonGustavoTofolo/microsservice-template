package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorNotFoundException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.DeleteFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import org.springframework.stereotype.Service;

@Service
public class DeleteFornecedorHttpPresenter implements DeleteFornecedorOutputPort {

    @Override
    public void fornecedorNaoEncontrado() throws BusinessRuleException {
        throw new FornecedorNotFoundException();
    }
}
