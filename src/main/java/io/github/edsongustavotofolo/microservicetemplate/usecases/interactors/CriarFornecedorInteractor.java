package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CriarFornecedorInputBoundary;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;

public class CriarFornecedorInteractor implements CriarFornecedorInputBoundary {

    private final FornecedorCriadoOutputBoundary presenter;
    private final FornecedorDsGateway fornecedorDsGateway;
    private final FornecedorMapper fornecedorMapper;

    public CriarFornecedorInteractor(FornecedorCriadoOutputBoundary presenter,
                                     FornecedorDsGateway fornecedorDsGateway,
                                     FornecedorMapper fornecedorMapper) {
        this.presenter = presenter;
        this.fornecedorDsGateway = fornecedorDsGateway;
        this.fornecedorMapper = fornecedorMapper;
    }

    @Override
    public NovoFornecedorResponseModel execute(final CreateFornecedorRequestModel requestModel) {
        if (Cnpj.numeroInvalido(requestModel.getCnpj())) {
            this.presenter.throwValidationError("CNPJ inválido!");
        }
        if (this.fornecedorDsGateway.existeFornecedorComCnpj(requestModel.getCnpj())) {
            this.presenter.throwValidationError("Já existe Fornecedor com o CNPJ informado!");
        }

        var fornecedor = this.fornecedorMapper.toDomain(requestModel);

        var id = this.fornecedorDsGateway.criar(fornecedor);

        return this.presenter.present(id);
    }
}
