package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface FornecedorJpaRepository
        extends RevisionRepository<FornecedorEntity, Integer, Integer>,
        JpaRepository<FornecedorEntity, Integer>,
        HibernateRepository<FornecedorEntity> {

    boolean existsByCnpj(final String cnpj);

    @EntityGraph(attributePaths = {"endereco", "endereco.cidade", "endereco.cidade.estado", "endereco.cidade.estado.pais", "contatos", "contatos.tipos"})
    @Override
    Optional<FornecedorEntity> findById(final Integer id);
}
