package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.CidadeEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CidadeJpaProvider implements CidadeProvider {

    private final CidadeJpaRepository cidadeJpaRepository;

    @Override
    public List<Cidade> getByName(final String name) {
        return this.cidadeJpaRepository.findByNomeContains(name).stream()
                .map(CidadeEntityMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public Optional<Cidade> getById(final Integer id) {
        return this.cidadeJpaRepository.findById(id)
                .map(CidadeEntityMapper.INSTANCE::toDomain);
    }
}
