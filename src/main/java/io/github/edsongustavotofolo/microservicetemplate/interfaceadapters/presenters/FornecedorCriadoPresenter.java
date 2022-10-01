package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorAlreadyExistsException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreatedFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreatedFornecedorOutputPort;
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
