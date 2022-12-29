package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.CidadeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Integer> {

    @EntityGraph(attributePaths = {"estado", "estado.pais"})
    List<CidadeEntity> findByNomeContains(final String nome);

    @EntityGraph(attributePaths = {"estado", "estado.pais"})
    @Override
    Optional<CidadeEntity> findById(Integer integer);
}
