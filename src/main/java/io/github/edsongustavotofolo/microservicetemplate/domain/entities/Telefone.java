package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Telefone extends ContatoTelefonico {
    public Telefone(final String ddd, final String numero) {
        super(ddd, numero);
    }

    public Telefone(final Integer id, final String ddd, final String numero) {
        super(ddd, numero);
        this.id = id;
    }

    @Override
    public boolean isSame(final Contato other) {
        if (other instanceof Telefone otherTelefone) {
            return this.ddd.equals(otherTelefone.getDdd()) && this.numero.equals(otherTelefone.getNumero());
        }
        return false;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof Telefone otherTelefone) {
            otherTelefone.ddd = this.ddd;
            otherTelefone.numero = this.numero;
        }
    }
}
