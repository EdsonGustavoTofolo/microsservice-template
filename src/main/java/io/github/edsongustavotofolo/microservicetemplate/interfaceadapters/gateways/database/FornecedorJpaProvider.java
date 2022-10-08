package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.FornecedorEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.FornecedorJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class FornecedorJpaProvider implements FornecedorProvider {

    private final FornecedorJpaRepository fornecedorJpaRepository;
    private final CidadeJpaRepository cidadeJpaRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Override
    public Integer create(final Fornecedor fornecedor) {
        log.info("Criando fornecedor");

        final var fornecedorEntity = this.getFornecedorEntity(fornecedor);

        final var saved = this.fornecedorJpaRepository.persist(fornecedorEntity);

        return saved.getId();
    }

    private FornecedorEntity getFornecedorEntity(final Fornecedor fornecedor) {
        final var cidadeEntity = this.cidadeJpaRepository.getReferenceById(fornecedor.getEndereco().getCidade().getId());

        final var fornecedorEntity = this.fornecedorEntityMapper.toEntity(fornecedor);
        fornecedorEntity.getEndereco().setCidade(cidadeEntity);
        fornecedorEntity.getEndereco().setFornecedor(fornecedorEntity);
        fornecedorEntity.getContatos().setFornecedor(fornecedorEntity);
        return fornecedorEntity;
    }

    @Override
    public boolean existsFornecedorWithCnpj(final String cnpj) {
        log.info("Verificando existencia de fornecedor pelo cnpj " + cnpj);
        return this.fornecedorJpaRepository.existsByCnpj(cnpj);
    }

    @Override
    public Optional<Fornecedor> getById(final Integer id) {
        return this.fornecedorJpaRepository.findById(id)
                .map(this.fornecedorEntityMapper::toDomain);
    }

    @Override
    public void update(final Fornecedor fornecedor) {
        final var fornecedorEntity = this.getFornecedorEntity(fornecedor);

        this.fornecedorJpaRepository.update(fornecedorEntity);
    }
}
