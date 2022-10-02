package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreatedFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateFornecedorInteractor implements CreateFornecedorInputPort {

    private final CreatedFornecedorOutputPort presenter;
    private final FornecedorProvider fornecedorProvider;
    private final FornecedorMapper fornecedorMapper;

    @Transactional
    @Override
    public CreatedFornecedor execute(final CreateFornecedor requestModel) throws BusinessRuleException {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.cnpjIsInvalid();
        }
        if (this.fornecedorProvider.existeFornecedorComCnpj(requestModel.getCnpj())) {
            this.presenter.fornecedorAlreadyExists();
        }

        final var fornecedor = this.fornecedorMapper.toDomain(requestModel);

        final var id = this.fornecedorProvider.criar(fornecedor);

        return this.presenter.present(id);
    }
}
