package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public class Email extends Contato {
    private String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public Email(Integer id, String endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    public void update(final String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return this.endereco;
    }
}
