package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.ContatoMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapperImpl implements ContatoMapper {

    @Override
    public Contato toDomain(final CreateContato contatoModel) {
        return switch (contatoModel.getTipoDeContato()) {
            case EMAIL:
                yield new Email(contatoModel.getEnderecoEmail());
            case TELEFONE:
                yield new Telefone(contatoModel.getDdd(), contatoModel.getNumero());
            case CELULAR:
                yield new Celular(contatoModel.getDdd(), contatoModel.getNumero());
            case SITE:
                yield new Site(contatoModel.getUrlSite());
            case OUTRO:
                yield new OutroContato(contatoModel.getTexto());
        };
    }

    @Override
    public Contato toDomain(final UpdateContato contatoModel) {
        return switch (contatoModel.getTipoDeContato()) {
            case EMAIL:
                yield new Email(contatoModel.getId(), contatoModel.getEnderecoEmail());
            case TELEFONE:
                yield new Telefone(contatoModel.getId(), contatoModel.getDdd(), contatoModel.getNumero());
            case CELULAR:
                yield new Celular(contatoModel.getId(), contatoModel.getDdd(), contatoModel.getNumero());
            case SITE:
                yield new Site(contatoModel.getId(), contatoModel.getUrlSite());
            case OUTRO:
                yield new OutroContato(contatoModel.getId(), contatoModel.getTexto());
        };
    }
}
