package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.ContatoTelefonico;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.TipoDeContatoEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.FornecedorEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers.TipoDeContatoEntityMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.CidadeJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.repository.FornecedorJpaRepository;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class FornecedorJpaProvider implements FornecedorProvider {

    private final FornecedorJpaRepository fornecedorJpaRepository;
    private final CidadeJpaRepository cidadeJpaRepository;

    @Override
    public Integer create(final Fornecedor fornecedor) {
        final var cidadeEntity = this.cidadeJpaRepository
                .getReferenceById(fornecedor.getEndereco().getCidade().getId());

        final var fornecedorEntity = FornecedorEntityMapper.INSTANCE.toEntity(fornecedor);

        fornecedorEntity.getEndereco().setCidade(cidadeEntity);
        fornecedorEntity.getEndereco().setFornecedor(fornecedorEntity);
        fornecedorEntity.getContatos().setFornecedor(fornecedorEntity);

        final var saved = this.fornecedorJpaRepository.persist(fornecedorEntity);

        return saved.getId();
    }

    @Override
    public boolean existsFornecedorWithCnpj(final String cnpj) {
        return this.fornecedorJpaRepository.existsByCnpj(cnpj);
    }

    @Override
    public Optional<Fornecedor> getById(final Integer id) {
        return this.fornecedorJpaRepository.findById(id)
                .map(FornecedorEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public void update(final Fornecedor fornecedor) {
        final var fornecedorEntity = this.fornecedorJpaRepository.getReferenceById(fornecedor.getId());

        final var cidadeEntity = this.cidadeJpaRepository
                .getReferenceById(fornecedor.getEndereco().getCidade().getId());

        fornecedorEntity.setRazaoSocial(fornecedor.getRazaoSocial());
        fornecedorEntity.setNomeFantasia(fornecedor.getNomeFantasia());
        fornecedorEntity.setObservacao(fornecedor.getObservacao());

        fornecedorEntity.getEndereco().setLogradouro(fornecedor.getEndereco().getLogradouro());
        fornecedorEntity.getEndereco().setNumero(fornecedor.getEndereco().getNumero());
        fornecedorEntity.getEndereco().setBairro(fornecedor.getEndereco().getBairro());
        fornecedorEntity.getEndereco().setComplemento(fornecedor.getEndereco().getComplemento());
        fornecedorEntity.getEndereco().setPontoDeReferencia(fornecedor.getEndereco().getPontoDeReferencia());
        fornecedorEntity.getEndereco().setCep(fornecedor.getEndereco().getCep().toString());
        fornecedorEntity.getEndereco().setCidade(cidadeEntity);

        fornecedorEntity.getContatos().setObservacao(fornecedor.getContatos().getObservacao());

        final List<TipoDeContatoEntity> removedEntities = new ArrayList<>();

        fornecedorEntity.getContatos().getTipos().forEach(tipoDeContatoEntity -> {
            final var exists = fornecedor.getContatos().getLista().stream()
                    .filter(contato -> nonNull(contato.getId()))
                    .anyMatch(contato -> contato.getId().equals(tipoDeContatoEntity.getId()));
            if (!exists) {
                removedEntities.add(tipoDeContatoEntity);
            }
        });

        fornecedorEntity.getContatos().getTipos().removeAll(removedEntities);

        for (final Contato contato : fornecedor.getContatos().getLista()) {
            fornecedorEntity.getContatos().getTipos().stream()
                    .filter(tipoDeContatoEntity -> tipoDeContatoEntity.getId().equals(contato.getId()))
                    .findFirst()
                    .ifPresentOrElse(tipoDeContatoEntity -> {
                        switch (tipoDeContatoEntity.getTipoDeContato()) {
                            case SITE -> tipoDeContatoEntity.setUrlSite(((Site) contato).getUrl());
                            case EMAIL -> tipoDeContatoEntity.setEnderecoEmail(((Email) contato).getEndereco());
                            case OUTRO -> tipoDeContatoEntity.setTexto(((OutroContato) contato).getTexto());
                            case CELULAR, TELEFONE -> {
                                tipoDeContatoEntity.setDdd(((ContatoTelefonico) contato).getDdd());
                                tipoDeContatoEntity.setNumero(((ContatoTelefonico) contato).getNumero());
                            }
                        }
                    }, () -> {
                        final var newTipoDeContatoEntity = TipoDeContatoEntityMapper.INSTANCE.map(contato);
                        fornecedorEntity.addContato(newTipoDeContatoEntity);
                    });
        }
    }

    @Override
    public boolean existsFornecedorById(final Integer id) {
        return this.fornecedorJpaRepository.existsById(id);
    }

    @Override
    public void delete(final Integer id) {
        this.fornecedorJpaRepository.findById(id)
                .ifPresent(this.fornecedorJpaRepository::delete);
    }
}
