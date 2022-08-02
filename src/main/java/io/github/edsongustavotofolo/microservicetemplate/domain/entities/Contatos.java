package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Contato;

import java.util.List;

public interface Contatos {
    Integer getId();
    String getObservacao();
    List<Contato> getLista();
    void adicionar(final Contato contato);
}
