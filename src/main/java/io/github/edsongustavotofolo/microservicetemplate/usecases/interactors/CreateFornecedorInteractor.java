package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreatedFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreatedFornecedorOutputPort;
import org.springframework.transaction.annotation.Transactional;

public class CreateFornecedorInteractor implements CreateFornecedorInputPort {

    private final CreatedFornecedorOutputPort presenter;
    private final FornecedorProvider fornecedorProvider;
    private final FornecedorMapper fornecedorMapper;

    public CreateFornecedorInteractor(CreatedFornecedorOutputPort presenter,
                                      FornecedorProvider fornecedorProvider,
                                      FornecedorMapper fornecedorMapper) {
        this.presenter = presenter;
        this.fornecedorProvider = fornecedorProvider;
        this.fornecedorMapper = fornecedorMapper;
    }

    @Transactional
    @Override
    public CreatedFornecedorModel execute(final CreateFornecedorModel requestModel) {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.cnpjIsInvalid();
        }
        if (this.fornecedorProvider.existeFornecedorComCnpj(requestModel.getCnpj())) {
            this.presenter.fornecedorAlreadyExists();
        }

        var fornecedor = this.fornecedorMapper.toDomain(requestModel);

        var id = this.fornecedorProvider.criar(fornecedor);

        return this.presenter.present(id);
    }
}
