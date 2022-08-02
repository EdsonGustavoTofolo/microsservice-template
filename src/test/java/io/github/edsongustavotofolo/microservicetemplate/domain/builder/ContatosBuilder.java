package io.github.edsongustavotofolo.microservicetemplate.domain.builder;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl.ContatosImpl;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.*;

public class ContatosBuilder {
    private Contatos contatos;

    private ContatosBuilder() {}

    public static ContatosBuilder umContatos() {
        var builder =  new ContatosBuilder();
        builder.contatos = new ContatosImpl();
        builder.contatos.adicionar(new Email("person@mymail.com"));
        builder.contatos.adicionar(new Telefone("49", "35202222"));
        builder.contatos.adicionar(new Celular("49", "991053588"));
        builder.contatos.adicionar(new Site("https://fornecedor.com"));
        builder.contatos.adicionar(new OutroContato("0800 8080 1234"));
        return builder;
    }

    public Contatos get() {
        return this.contatos;
    }
}
