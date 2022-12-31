package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class Contato {
    @EqualsAndHashCode.Include
    protected Integer id;

    public abstract boolean isSame(final Contato other);

    public abstract void update(final Contato other);
}
