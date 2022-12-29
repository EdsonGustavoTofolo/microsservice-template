package io.github.edsongustavotofolo.microservicetemplate.usecases.providers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;

import java.util.List;
import java.util.Optional;

public interface CidadeProvider {
    List<Cidade> getByName(final String name);
    boolean existsById(final Integer id);
    Optional<Cidade> getById(final Integer id);
}
