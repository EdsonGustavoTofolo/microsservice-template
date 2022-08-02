package io.github.edsongustavotofolo.microservicetemplate.usecases.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class NovoFornecedorResponseModel {
    private final Integer id;

    public Integer get() {
        return this.id;
    }
}
