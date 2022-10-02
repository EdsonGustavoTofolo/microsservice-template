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
    private final FornecedorMapper fornecedorMapper;

    @Transactional
    @Override
    public void execute(final CreateFornecedor requestModel) throws BusinessRuleException {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.cnpjIsInvalid();
        }
        if (this.fornecedorProvider.existeFornecedorComCnpj(requestModel.getCnpj())) {
            this.presenter.fornecedorAlreadyExists();
        }

        final var fornecedor = this.fornecedorMapper.toDomain(requestModel);

        final var id = this.fornecedorProvider.criar(fornecedor);

        this.presenter.show(id);
    }
}
