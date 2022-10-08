package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;

public class ContatosBuilder {
    private Contatos contatos;

    private ContatosBuilder() {
    }

    public static ContatosBuilder umContatos() {
        final var builder = new ContatosBuilder();
        builder.contatos = new ContatosImpl();
        builder.contatos.add(new Email(1, "persona@mymail.com"));
        builder.contatos.add(new Telefone(2, "49", "35202222"));
        builder.contatos.add(new Celular(3, "49", "991053588"));
        builder.contatos.add(new Site(4, "https://fornecedor.com"));
        builder.contatos.add(new OutroContato(5, "0800 8080 1234"));
        return builder;
    }

    public Contatos get() {
        return this.contatos;
    }
}
