package io.github.edsongustavotofolo.microservicetemplate.usecases.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreatedFornecedorModel {
    private final Integer id;

    public Integer get() {
        return this.id;
    }
}
