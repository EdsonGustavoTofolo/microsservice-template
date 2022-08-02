package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Celular extends ContatoTelefonico {
    public Celular(String ddd, String numero) {
        super(ddd, numero);
    }
}
