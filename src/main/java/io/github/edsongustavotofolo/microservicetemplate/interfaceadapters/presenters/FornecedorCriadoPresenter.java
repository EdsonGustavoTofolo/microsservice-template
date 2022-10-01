package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorAlreadyExistsException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreatedFornecedorOutputPort;
import org.springframework.stereotype.Service;

@Service
public class FornecedorCriadoPresenter implements CreatedFornecedorOutputPort {

    @Override
    public CreatedFornecedorModel present(final Integer id) {
        return new CreatedFornecedorModel(id);
    }

    @Override
    public void cnpjIsInvalid() throws BusinessRuleException {
        throw new FornecedorCnpjInvalidException();
    }

    @Override
    public void fornecedorAlreadyExists() throws BusinessRuleException {
        throw new FornecedorAlreadyExistsException();
    }
}
