package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Telefone extends ContatoTelefonico {
    public Telefone(String ddd, String numero) {
        super(ddd, numero);
    }
}
