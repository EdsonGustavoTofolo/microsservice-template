package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CidadeJpaProvider implements CidadeProvider {

    private final CidadeJpaRepository cidadeJpaRepository;

    @Override
    public Optional<Cidade> getByName(final String name) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        return this.cidadeJpaRepository.existsById(id);
    }
}
