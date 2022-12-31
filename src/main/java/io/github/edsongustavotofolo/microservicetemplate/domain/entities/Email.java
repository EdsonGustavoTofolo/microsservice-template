package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;

public class Email extends Contato {
    private String endereco;

    public Email(final String endereco) {
        this.endereco = endereco;
    }

    public Email(final Integer id, final String endereco) {
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

    @Override
    public void update(final Contato other) {
        if (other instanceof Email otherEmail) {
            otherEmail.endereco = this.endereco;
        }
    }

    @Override
    public TipoDeContatoEnum getTipo() {
        return TipoDeContatoEnum.EMAIL;
    }

    public String getEndereco() {
        return this.endereco;
    }
}
