package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedorModel;

public class NovoFornecedorResponseModelBuilder {
    private CreatedFornecedorModel responseModel;

    private NovoFornecedorResponseModelBuilder() {}

    public static NovoFornecedorResponseModelBuilder umFornecedorResponseModel() {
        var builder = new NovoFornecedorResponseModelBuilder();
        builder.responseModel = new CreatedFornecedorModel(1);
        return builder;
    }

    public CreatedFornecedorModel get() {
        return this.responseModel;
    }
}
