package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EnderecoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.FornecedorImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapperImpl implements FornecedorMapper {

    private final ContatoMapper contatoMapper;

    public FornecedorMapperImpl(final ContatoMapper contatoMapper) {
        this.contatoMapper = contatoMapper;
    }

    @Override
    public Fornecedor toDomain(final CreateFornecedor requestModel) {
        final var endereco = new EnderecoImpl(
                requestModel.getLogradouro(),
                requestModel.getNumero(),
                requestModel.getBairro(),
                requestModel.getComplemento(),
                requestModel.getPontoDeReferencia(),
                new Cep(requestModel.getCep()),
                new CidadeImpl(requestModel.getCidade()));

        final var contatos = new ContatosImpl();
        contatos.setObservacao(requestModel.getObservacaoContatos());
        requestModel.getContatos().forEach(contatoModel -> {
            final var contato = this.contatoMapper.toDomain(contatoModel);
            contatos.add(contato);
        });

        return new FornecedorImpl(
                new Cnpj(requestModel.getCnpj()),
                requestModel.getRazaoSocial(),
                requestModel.getNomeFantasia(), requestModel.getObservacao(),
                endereco,
                contatos);
    }
}
