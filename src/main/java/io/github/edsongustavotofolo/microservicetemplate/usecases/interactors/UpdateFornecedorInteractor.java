package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.UpdateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.UpdateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UpdateFornecedorInteractor implements UpdateFornecedorInputPort {

    private final FornecedorProvider fornecedorProvider;
    private final UpdateFornecedorOutputPort presenter;

    @Transactional
    @Override
    public void execute(final UpdateFornecedor updateFornecedor) throws BusinessRuleException {
        final var fornecedor = this.fornecedorProvider.getById(updateFornecedor.getId())
                .orElseThrow(this.presenter::fornecedorNaoEncontrado);

        fornecedor.setNomeFantasia(updateFornecedor.getNomeFantasia());
        fornecedor.setRazaoSocial(updateFornecedor.getRazaoSocial());
        fornecedor.setObservacao(updateFornecedor.getObservacao());
        fornecedor.setEndereco(
                updateFornecedor.getLogradouro(),
                updateFornecedor.getNumero(),
                updateFornecedor.getBairro(),
                updateFornecedor.getComplemento(),
                updateFornecedor.getPontoDeReferencia(),
                updateFornecedor.getCep(),
                updateFornecedor.getCidade());

        fornecedor.getContatos().setObservacao(updateFornecedor.getObservacaoContatos());

        final List<Contato> removedContatos = new ArrayList<>();
        fornecedor.getContatos().getLista().forEach(contato -> {
            updateFornecedor.getContatos().stream()
                    .map(ContatoMapper.INSTANCE::map)
                    .filter(updateContato -> updateContato.equals(contato))
                    .findFirst()
                    .ifPresentOrElse(
                            updatableContato -> updatableContato.update(contato),
                            () -> removedContatos.add(contato)
                            );
        });
        if (!removedContatos.isEmpty()) {
            fornecedor.getContatos().removeAll(removedContatos);
        }

        updateFornecedor.getContatos().forEach(updateContato -> {
            if (isNull(updateContato.getId())) {
                fornecedor.getContatos().add(ContatoMapper.INSTANCE.map(updateContato));
            }
        });

        this.fornecedorProvider.update(fornecedor);
    }
}
