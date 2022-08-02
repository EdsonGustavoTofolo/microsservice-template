package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;

public class NovoFornecedorResponseModelBuilder {
    private NovoFornecedorResponseModel responseModel;

    private NovoFornecedorResponseModelBuilder() {}

    public static NovoFornecedorResponseModelBuilder umFornecedorResponseModel() {
        var builder = new NovoFornecedorResponseModelBuilder();
        builder.responseModel = new NovoFornecedorResponseModel(1);
        return builder;
    }

    public NovoFornecedorResponseModel get() {
        return this.responseModel;
    }
}
