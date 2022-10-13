package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;

import java.util.List;

public interface GetCidadesByNameOuputPort<T> {
    void show(final List<Cidade> cidades);
    List<T> get();
}
