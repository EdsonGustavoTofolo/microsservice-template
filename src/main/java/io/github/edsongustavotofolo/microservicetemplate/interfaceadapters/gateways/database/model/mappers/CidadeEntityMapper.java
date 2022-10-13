package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.CidadeImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.EstadoImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.PaisImpl;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.database.model.CidadeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CidadeEntityMapper {
    default Cidade toDomain(final CidadeEntity entity) {
        return new CidadeImpl(
                entity.getId(),
                entity.getNome(),
                new EstadoImpl(
                        entity.getEstado().getId(),
                        entity.getEstado().getNome(),
                        entity.getEstado().getSigla(),
                        new PaisImpl(
                                entity.getEstado().getPais().getId(),
                                entity.getEstado().getPais().getNome()
                        )
                )
        );
    }
}
