package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

public abstract class ContatoTelefonico extends Contato {
    protected final String ddd;
    protected final String numero;

    public ContatoTelefonico(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }
}
