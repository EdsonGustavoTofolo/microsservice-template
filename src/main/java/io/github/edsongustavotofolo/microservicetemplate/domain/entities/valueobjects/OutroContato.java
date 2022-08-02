package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public final class OutroContato extends Contato {
    private final String texto;

    @Override
    public String toString() {
        return this.texto;
    }
}
