package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.*;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;

public class ContatosBuilder {
    private Contatos contatos;

    private ContatosBuilder() {}

    public static ContatosBuilder umContatos() {
        var builder = new ContatosBuilder();
        builder.contatos = new ContatosImpl();
        builder.contatos.adicionar(new Email(1, "persona@mymail.com"));
        builder.contatos.adicionar(new Telefone(2, "49", "35202222"));
        builder.contatos.adicionar(new Celular(3, "49", "991053588"));
        builder.contatos.adicionar(new Site(4, "https://fornecedor.com"));
        builder.contatos.adicionar(new OutroContato(5, "0800 8080 1234"));
        return builder;
    }

    public Contatos get() {
        return this.contatos;
    }
}
