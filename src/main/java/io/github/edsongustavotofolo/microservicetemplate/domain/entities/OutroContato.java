package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;

public final class OutroContato extends Contato {
    private String texto;

    public OutroContato(final String texto) {
        this.texto = texto;
    }

    public OutroContato(final Integer id, final String texto) {
        this.texto = texto;
        this.id = id;
    }

    public void update(final String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return this.texto;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof OutroContato otherOutroContato) {
            otherOutroContato.texto = this.texto;
        }
    }

    @Override
    public TipoDeContatoEnum getTipo() {
        return TipoDeContatoEnum.OUTRO;
    }

    public String getTexto() {
        return this.texto;
    }
}
