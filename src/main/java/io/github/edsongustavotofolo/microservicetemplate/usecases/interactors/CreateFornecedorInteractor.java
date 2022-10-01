package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreatedFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreatedFornecedorOutputPort;
import org.springframework.transaction.annotation.Transactional;

public class CreateFornecedorInteractor implements CreateFornecedorInputPort {

    private final CreatedFornecedorOutputPort presenter;
    private final FornecedorDsGateway fornecedorDsGateway;
    private final FornecedorMapper fornecedorMapper;

    public CreateFornecedorInteractor(CreatedFornecedorOutputPort presenter,
                                      FornecedorDsGateway fornecedorDsGateway,
                                      FornecedorMapper fornecedorMapper) {
        this.presenter = presenter;
        this.fornecedorDsGateway = fornecedorDsGateway;
        this.fornecedorMapper = fornecedorMapper;
    }

    @Transactional
    @Override
    public CreatedFornecedorModel execute(final CreateFornecedorModel requestModel) {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.cnpjIsInvalid();
        }
        if (this.fornecedorDsGateway.existeFornecedorComCnpj(requestModel.getCnpj())) {
            this.presenter.fornecedorAlreadyExists();
        }

        var fornecedor = this.fornecedorMapper.toDomain(requestModel);

        var id = this.fornecedorDsGateway.criar(fornecedor);

        return this.presenter.present(id);
    }
}
