package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;

public final class Telefone extends ContatoTelefonico {
    public Telefone(final String ddd, final String numero) {
        super(ddd, numero);
    }

    public Telefone(final Integer id, final String ddd, final String numero) {
        super(ddd, numero);
        this.id = id;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof Telefone otherTelefone) {
            otherTelefone.ddd = this.ddd;
            otherTelefone.numero = this.numero;
        }
    }

    @Override
    public TipoDeContatoEnum getTipo() {
        return TipoDeContatoEnum.TELEFONE;
    }
}
