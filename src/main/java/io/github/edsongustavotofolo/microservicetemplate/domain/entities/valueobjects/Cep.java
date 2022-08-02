package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Cep {
    private final String numero;

    public Cep(final String numero) {
        if (numero == null || !numero.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        return this.numero;
    }
}
