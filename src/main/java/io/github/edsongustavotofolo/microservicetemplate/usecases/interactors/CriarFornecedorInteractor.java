package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
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
    public NovoFornecedorResponseModel execute(final CriarFornecedorRequestModel criarFornecedorRequestModel) {
        var fornecedor = this.fornecedorMapper.toDomain(criarFornecedorRequestModel);

        var id = this.fornecedorDsGateway.criar(fornecedor);

        return this.presenter.present(id);
    }
}
