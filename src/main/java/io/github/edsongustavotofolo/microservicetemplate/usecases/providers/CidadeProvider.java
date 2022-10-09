package io.github.edsongustavotofolo.microservicetemplate.usecases.providers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;

import java.util.Optional;

public interface CidadeProvider {
    Optional<Cidade> getByName(final String name);
    boolean existsById(final Integer id);
}
