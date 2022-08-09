package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Celular extends ContatoTelefonico {
    public Celular(String ddd, String numero) {
        super(ddd, numero);
    }

    public Celular(Integer id, String ddd, String numero) {
        super(ddd, numero);
        this.id = id;
    }
}
