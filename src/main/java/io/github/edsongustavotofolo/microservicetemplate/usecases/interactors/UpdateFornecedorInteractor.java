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

@Service
@RequiredArgsConstructor
public class UpdateFornecedorInteractor implements UpdateFornecedorInputPort {

    private final FornecedorProvider fornecedorProvider;
    private final UpdateFornecedorOutputPort presenter;

    @Transactional
    @Override
    public void execute(final UpdateFornecedor updateFornecedor) throws BusinessRuleException {
        final var fornecedor = this.fornecedorProvider.getById(updateFornecedor.getId())
                .orElse(this.presenter.fornecedorNaoEncontrado());

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
                    .filter(updateContato -> updateContato.isSame(contato))
                    .findFirst()
                    .ifPresentOrElse(
                            updatableContato -> updatableContato.update(contato),
                            () -> removedContatos.add(contato)
                            );
        });
        if (!removedContatos.isEmpty()) {
            //
        }

//        final List<Contato> novoContato = new ArrayList<>();
//        updateFornecedor.getContatos()
//                .forEach(contatoRequestModel -> {
//                    final var contatoRequest = ContatoMapper.INSTANCE.map(contatoRequestModel);
//                    fornecedor.getContatos()
//                            .getLista().stream()
//                            .filter(contato -> contato.equals(contatoRequest))
//                            .findFirst()
//                            .ifPresentOrElse(contato -> {
//                                switch (contatoRequestModel.getTipoDeContato()) {
//                                    case EMAIL -> {
//                                        final var email = (Email) contato;
//                                        email.update(contatoRequestModel.getEnderecoEmail());
//                                    }
//                                    case TELEFONE -> {
//                                        final var telefone = (Telefone) contato;
//                                        telefone.update(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
//                                    }
//                                    case CELULAR -> {
//                                        final var celular = (Celular) contato;
//                                        celular.update(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
//                                    }
//                                    case SITE -> {
//                                        final var site = (Site) contato;
//                                        site.update(contatoRequestModel.getUrlSite());
//                                    }
//                                    case OUTRO -> {
//                                        final var outro = (OutroContato) contato;
//                                        outro.update(contatoRequestModel.getTexto());
//                                    }
//                                }
//                            }, () -> novoContato.add(contatoRequest));
//
//                });
//        if (!novoContato.isEmpty()) {
//            novoContato.forEach(contato -> fornecedor.getContatos().add(contato));
//        }


        this.fornecedorProvider.update(fornecedor);
    }
}
