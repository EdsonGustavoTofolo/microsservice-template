package io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.UpdateContatoModel;

public interface ContatoMapper {
    Contato toDomain(final CreateContatoModel contatoModel);
    Contato toDomain(final UpdateContatoModel contatoModel);
}
