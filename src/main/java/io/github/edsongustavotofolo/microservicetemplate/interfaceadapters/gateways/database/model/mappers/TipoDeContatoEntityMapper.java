package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.TipoDeContatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoDeContatoEntityMapper {

    TipoDeContatoEntityMapper INSTANCE = Mappers.getMapper(TipoDeContatoEntityMapper.class);

    default TipoDeContatoEntity map(final Contato contato) {
        final TipoDeContatoEntity tipoDeContatoEntity;
        if (contato instanceof Email email) {
            tipoDeContatoEntity = TipoDeContatoEntity.email(email.toString());
        } else if (contato instanceof Site site) {
            tipoDeContatoEntity = TipoDeContatoEntity.site(site.toString());
        } else if (contato instanceof Telefone telefone) {
            tipoDeContatoEntity = TipoDeContatoEntity.telefone(telefone.getDdd(), telefone.getNumero());
        } else if (contato instanceof Celular celular) {
            tipoDeContatoEntity = TipoDeContatoEntity.celular(celular.getDdd(), celular.getNumero());
        } else {
            final var outroContato = (OutroContato) contato;
            tipoDeContatoEntity = TipoDeContatoEntity.outro(outroContato.toString());
        }
        return tipoDeContatoEntity;
    }
}
