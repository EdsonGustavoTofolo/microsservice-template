package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;

public class NovoFornecedorResponseModelBuilder {
    private CreatedFornecedor responseModel;

    private NovoFornecedorResponseModelBuilder() {}

    public static NovoFornecedorResponseModelBuilder umFornecedorResponseModel() {
        var builder = new NovoFornecedorResponseModelBuilder();
        builder.responseModel = new CreatedFornecedor(1);
        return builder;
    }

    public CreatedFornecedor get() {
        return this.responseModel;
    }
}
