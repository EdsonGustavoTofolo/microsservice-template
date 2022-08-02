package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public final class Site extends Contato {
    private final String url;

    @Override
    public String toString() {
        return this.url;
    }
}
