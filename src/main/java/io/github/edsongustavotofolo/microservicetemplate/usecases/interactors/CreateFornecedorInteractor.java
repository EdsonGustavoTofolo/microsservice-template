package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFornecedorInteractor implements CreateFornecedorInputPort {

    private final CreateFornecedorOutputPort<?> presenter;
    private final FornecedorProvider fornecedorProvider;

    @Transactional
    @Override
    public void execute(final CreateFornecedor requestModel) throws BusinessRuleException {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.cnpjIsInvalid();
            return;
        }
        if (this.fornecedorProvider.existsFornecedorWithCnpj(requestModel.getCnpj())) {
            this.presenter.fornecedorAlreadyExists();
            return;
        }

        final var fornecedor = FornecedorMapper.INSTANCE.map(requestModel);

        final var id = this.fornecedorProvider.create(fornecedor);

        this.presenter.show(id);
    }
}
