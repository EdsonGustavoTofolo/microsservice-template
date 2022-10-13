package io.github.edsongustavotofolo.microservicetemplate.usecases.providers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;

import java.util.List;

public interface CidadeProvider {
    List<Cidade> getByName(final String name);
    boolean existsById(final Integer id);
}
