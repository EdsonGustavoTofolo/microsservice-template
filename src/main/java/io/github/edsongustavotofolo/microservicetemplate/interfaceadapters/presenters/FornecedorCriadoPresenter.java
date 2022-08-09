package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.domain.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;
import org.springframework.stereotype.Service;

@Service
public class FornecedorCriadoPresenter implements FornecedorCriadoOutputBoundary {

    @Override
    public NovoFornecedorResponseModel present(Integer id) {
        return new NovoFornecedorResponseModel(id);
    }

    @Override
    public void throwValidationError(String mensagem) throws BusinessRuleException {
        throw new BusinessRuleException(mensagem);
    }
}
