package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.AtualizarFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.FornecedorAtualizadoOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtualizarFornecedorInteractor implements AtualizarFornecedorInputPort {

    private final FornecedorProvider fornecedorProvider;
    private final FornecedorAtualizadoOutputPort presenter;
    private final ContatoMapper contatoMapper;
    
    @Transactional
    @Override
    public void execute(final Integer id, final UpdateFornecedor requestModel) throws BusinessRuleException {
        var fornecedor = this.fornecedorProvider.buscarPorId(id)
                .orElse(presenter.fornecedorNaoEncontrado());

        fornecedor.setCnpj(new Cnpj(requestModel.getCnpj()));
        fornecedor.setNomeFantasia(requestModel.getNomeFantasia());
        fornecedor.setRazaoSocial(requestModel.getRazaoSocial());
        fornecedor.setObservacao(requestModel.getObservacao());
        fornecedor.setEndereco(
                requestModel.getLogradouro(),
                requestModel.getNumero(),
                requestModel.getBairro(),
                requestModel.getComplemento(),
                requestModel.getPontoDeReferencia(),
                requestModel.getCep(),
                requestModel.getCidadeId());

        List<Contato> contatosParaAdicionar = new ArrayList<>();

        requestModel.getContatos()
                .forEach(contatoRequestModel -> {
                    Contato contatoRequest = contatoMapper.toDomain(contatoRequestModel);
                    fornecedor.getContatos()
                            .getLista().stream()
                            .filter(contato -> contato.equals(contatoRequest))
                            .findFirst()
                            .ifPresentOrElse(contato -> {
                                switch (contatoRequestModel.getTipoDeContato()) {
                                    case EMAIL -> {
                                        var email = (Email) contato;
                                        email.update(contatoRequestModel.getEnderecoEmail());
                                    }
                                    case TELEFONE -> {
                                        var telefone = (Telefone) contato;
                                        telefone.update(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                    }
                                    case CELULAR -> {
                                        var celular = (Celular) contato;
                                        celular.update(contatoRequestModel.getDdd(), contatoRequestModel.getNumero());
                                    }
                                    case SITE -> {
                                        var site = (Site) contato;
                                        site.update(contatoRequestModel.getUrlSite());
                                    }
                                    case OUTRO -> {
                                        var outro = (OutroContato) contato;
                                        outro.update(contatoRequestModel.getTexto());
                                    }
                                }
                            }, () -> {
                                contatosParaAdicionar.add(contatoRequest);
                            });

                });
        if (!contatosParaAdicionar.isEmpty()) {
            contatosParaAdicionar.forEach(contato -> fornecedor.getContatos().adicionar(contato));
        }
        this.fornecedorProvider.atualizar(fornecedor);
    }
}
