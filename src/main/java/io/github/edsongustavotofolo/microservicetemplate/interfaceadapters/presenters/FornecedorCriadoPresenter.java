package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorAlreadyExistsException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;
import org.springframework.stereotype.Service;

@Service
public class FornecedorCriadoPresenter implements FornecedorCriadoOutputBoundary {

    @Override
    public NovoFornecedorResponseModel present(final Integer id) {
        return new NovoFornecedorResponseModel(id);
    }

    @Override
    public void cnpjInvalido() throws BusinessRuleException {
        throw new FornecedorCnpjInvalidException();
    }

    @Override
    public void fornecedorJaExisteComCnpj() throws BusinessRuleException {
        throw new FornecedorAlreadyExistsException();
    }
}
