package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorAlreadyExistsException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import org.springframework.stereotype.Service;

@Service
public class FornecedorCriadoHttpPresenter implements CreateFornecedorOutputPort<CreatedFornecedor> {

    private CreatedFornecedor createdFornecedor;

    @Override
    public void present(final Integer id) {
        this.createdFornecedor = new CreatedFornecedor(id);
    }

    @Override
    public CreatedFornecedor get() {
        return this.createdFornecedor;
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
