package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.FornecedorEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.FornecedorJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FornecedorJpaGateway implements FornecedorDsGateway {

    private final FornecedorJpaRepository fornecedorJpaRepository;
    private final CidadeJpaRepository cidadeJpaRepository;
    private final FornecedorEntityMapper fornecedorEntityMapper;

    @Transactional
    @Override
    public Integer criar(final Fornecedor fornecedor) {
        log.info("Criando fornecedor");

        var cidadeEntity = this.cidadeJpaRepository.getReferenceById(fornecedor.getEndereco().getCidade().getId());

        var fornecedorEntity = this.fornecedorEntityMapper.toEntity(fornecedor);
        fornecedorEntity.getEndereco().setCidade(cidadeEntity);
        fornecedorEntity.getEndereco().setFornecedor(fornecedorEntity);
        fornecedorEntity.getContatos().setFornecedor(fornecedorEntity);

        var saved = this.fornecedorJpaRepository.persist(fornecedorEntity);

        return saved.getId();
    }

    @Override
    public boolean existeFornecedorComCnpj(final String cnpj) {
        log.info("Verificando existencia de fornecedor pelo cnpj " + cnpj);
        return this.fornecedorJpaRepository.existsByCnpj(cnpj);
    }

    @Override
    public Optional<Fornecedor> buscarPorId(Integer id) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        throw new RuntimeException("Not implemented yet");
    }
}
