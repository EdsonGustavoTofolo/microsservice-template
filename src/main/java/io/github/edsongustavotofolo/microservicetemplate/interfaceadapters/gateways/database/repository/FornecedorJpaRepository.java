package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface FornecedorJpaRepository
        extends RevisionRepository<FornecedorEntity, Integer, Integer>,
                JpaRepository<FornecedorEntity, Integer>,
                HibernateRepository<FornecedorEntity> {
}
