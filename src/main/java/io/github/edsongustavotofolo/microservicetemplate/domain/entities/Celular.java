package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Celular extends ContatoTelefonico {
    public Celular(final String ddd, final String numero) {
        super(ddd, numero);
    }

    public Celular(final Integer id, final String ddd, final String numero) {
        super(ddd, numero);
        this.id = id;
    }

    @Override
    public boolean isSame(final Contato other) {
        if (other instanceof Celular otherCelular) {
            return this.ddd.equals(otherCelular.getDdd()) && this.numero.equals(otherCelular.getNumero());
        }
        return false;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof Celular otherCelular) {
            otherCelular.ddd = this.ddd;
            otherCelular.numero = this.numero;
        }
    }
}
