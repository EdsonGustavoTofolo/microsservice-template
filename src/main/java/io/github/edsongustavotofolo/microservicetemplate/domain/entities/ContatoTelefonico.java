package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public abstract class ContatoTelefonico extends Contato {
    protected String ddd;
    protected String numero;

    public ContatoTelefonico(final String ddd, final String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public void update(final String ddd, final String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return this.ddd;
    }

    public String getNumero() {
        return this.numero;
    }
}
