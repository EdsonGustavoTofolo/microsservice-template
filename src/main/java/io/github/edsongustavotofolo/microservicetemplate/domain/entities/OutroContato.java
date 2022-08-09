package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class OutroContato extends Contato {
    private String texto;

    public OutroContato(String texto) {
        this.texto = texto;
    }

    public OutroContato(Integer id, String texto) {
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
}
