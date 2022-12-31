package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;

public final class Celular extends ContatoTelefonico {
    public Celular(final String ddd, final String numero) {
        super(ddd, numero);
    }

    public Celular(final Integer id, final String ddd, final String numero) {
        super(ddd, numero);
        this.id = id;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof Celular otherCelular) {
            otherCelular.ddd = this.ddd;
            otherCelular.numero = this.numero;
        }
    }

    @Override
    public TipoDeContatoEnum getTipo() {
        return TipoDeContatoEnum.CELULAR;
    }
}
