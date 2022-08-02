package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public final class Email extends Contato {
    private final String endereco;

    @Override
    public String toString() {
        return this.endereco;
    }
}
