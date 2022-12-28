package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;

public class NovoFornecedorResponseModelFixture {
    private CreatedFornecedor responseModel;

    private NovoFornecedorResponseModelFixture() {}

    public static NovoFornecedorResponseModelFixture umFornecedorResponseModel() {
        var builder = new NovoFornecedorResponseModelFixture();
        builder.responseModel = new CreatedFornecedor(1);
        return builder;
    }

    public CreatedFornecedor get() {
        return this.responseModel;
    }
}
