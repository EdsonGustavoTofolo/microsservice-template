package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.*;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.ContatosEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.FornecedorEntity;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.TipoDeContatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface FornecedorEntityMapper {

    @Mapping(target = "cnpj", expression = "java(fornecedor.getCnpj().toString())")
    @Mapping(target = "endereco.cep", expression = "java(endereco.getCep().toString())")
    @Mapping(target = "contatos", source = "contatos", qualifiedByName = "contatosToContatosEntity")
    FornecedorEntity toEntity(final Fornecedor fornecedor);

    @Named("contatosToContatosEntity")
    static ContatosEntity contatosListaToTipoDeContatoEntity(final Contatos contatos) {
        var contatosEntity = new ContatosEntity();

        List<TipoDeContatoEntity> tipoDeContatoEntities = contatos.getLista().stream().map(contato -> {
            TipoDeContatoEntity tipoDeContatoEntity;
            if (contato instanceof Email email) {
                tipoDeContatoEntity = TipoDeContatoEntity.email(email.toString());
            } else if (contato instanceof Site site) {
                tipoDeContatoEntity = TipoDeContatoEntity.site(site.toString());
            } else if (contato instanceof Telefone telefone) {
                tipoDeContatoEntity = TipoDeContatoEntity.telefone(telefone.getDdd(), telefone.getNumero());
            } else if (contato instanceof Celular celular) {
                tipoDeContatoEntity = TipoDeContatoEntity.celular(celular.getDdd(), celular.getNumero());
            } else {
                var outroContato = (OutroContato) contato;
                tipoDeContatoEntity = TipoDeContatoEntity.outro(outroContato.toString());
            }
            tipoDeContatoEntity.setContatos(contatosEntity);
            return tipoDeContatoEntity;
        }).toList();

        contatosEntity.setTipos(tipoDeContatoEntities);
        contatosEntity.setObservacao(contatos.getObservacao());

        return contatosEntity;
    }
}
