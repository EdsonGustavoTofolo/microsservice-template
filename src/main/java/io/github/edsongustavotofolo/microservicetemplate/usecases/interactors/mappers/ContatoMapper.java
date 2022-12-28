package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    default Contatos map(final List<CreateContato> value) {
        final var contatos = value.stream().map(createContato -> {
            final var contato = switch (createContato.getTipoDeContato()) {
                case EMAIL:
                    yield new Email(createContato.getEnderecoEmail());
                case TELEFONE:
                    yield new Telefone(createContato.getDdd(), createContato.getNumero());
                case CELULAR:
                    yield new Celular(createContato.getDdd(), createContato.getNumero());
                case SITE:
                    yield new Site(createContato.getUrlSite());
                case OUTRO:
                    yield new OutroContato(createContato.getTexto());
            };
            return contato;
        }).toList();

        final var contatosImpl = new ContatosImpl();

        contatos.forEach(contatosImpl::add);

        return contatosImpl;
    }

    default Contato map(final UpdateContato updateContato) {
        return switch (updateContato.getTipoDeContato()) {
            case EMAIL:
                yield new Email(updateContato.getId(), updateContato.getEnderecoEmail());
            case TELEFONE:
                yield new Telefone(updateContato.getId(), updateContato.getDdd(), updateContato.getNumero());
            case CELULAR:
                yield new Celular(updateContato.getId(), updateContato.getDdd(), updateContato.getNumero());
            case SITE:
                yield new Site(updateContato.getId(), updateContato.getUrlSite());
            case OUTRO:
                yield new OutroContato(updateContato.getId(), updateContato.getTexto());
        };
    }
}
