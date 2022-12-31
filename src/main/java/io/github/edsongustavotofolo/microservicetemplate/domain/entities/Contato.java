package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class Contato {
    @EqualsAndHashCode.Include
    protected Integer id;

    public abstract void update(final Contato other);
    public abstract TipoDeContatoEnum getTipo();
}
