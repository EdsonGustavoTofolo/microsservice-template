package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Telefone extends ContatoTelefonico {
    public Telefone(String ddd, String numero) {
        super(ddd, numero);
    }

    public Telefone(Integer id, String ddd, String numero) {
        super(ddd, numero);
        this.id = id;
    }
}
