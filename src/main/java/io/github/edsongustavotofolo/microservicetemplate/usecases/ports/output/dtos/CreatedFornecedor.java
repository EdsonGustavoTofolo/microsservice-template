package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreatedFornecedor {
    private final Integer id;

    public Integer get() {
        return this.id;
    }
}