package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Integer> {

    List<CidadeEntity> findByNomeContains(final String nome);
}
