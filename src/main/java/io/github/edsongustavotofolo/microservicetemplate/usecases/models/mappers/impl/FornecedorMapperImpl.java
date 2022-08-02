package io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EnderecoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.*;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;

public class FornecedorMapperImpl implements FornecedorMapper {

    @Override
    public Fornecedor toDomain(final CriarFornecedorRequestModel requestModel) {
        var endereco = new EnderecoImpl(
                requestModel.getLogradouro(),
                requestModel.getNumero(),
                requestModel.getBairro(),
                requestModel.getComplemento(),
                requestModel.getPontoDeReferencia(),
                new Cep(requestModel.getCep()),
                new CidadeImpl(requestModel.getCidadeId()));

        var contatos = new ContatosImpl();
        contatos.setObservacao(requestModel.getObservacaoContatos());
        requestModel.getContatos().forEach(contatoModel -> {
            Contato contato = switch (contatoModel.getTipoDeContato()) {
                case EMAIL: yield new Email(contatoModel.getEnderecoEmail());
                case TELEFONE: yield new Telefone(contatoModel.getDdd(), contatoModel.getNumero());
                case CELULAR: yield new Celular(contatoModel.getDdd(), contatoModel.getNumero());
                case SITE: yield new Site(contatoModel.getUrlSite());
                case OUTRO: yield new OutroContato(contatoModel.getTexto());
            };
            contatos.adicionar(contato);
        });

        return new FornecedorImpl(
                new Cnpj(requestModel.getCnpj()),
                requestModel.getRazaoSocial(),
                requestModel.getNomeFantasia(), requestModel.getObservacao(),
                endereco,
                contatos);
    }
}
