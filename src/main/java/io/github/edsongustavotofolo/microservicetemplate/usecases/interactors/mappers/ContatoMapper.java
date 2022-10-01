package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.UpdateContatoModel;

public interface ContatoMapper {
    Contato toDomain(final CreateContatoModel contatoModel);
    Contato toDomain(final UpdateContatoModel contatoModel);
}
