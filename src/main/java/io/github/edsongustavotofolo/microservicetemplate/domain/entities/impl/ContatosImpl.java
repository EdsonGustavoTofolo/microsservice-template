package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Contatos;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatosImpl implements Contatos {
    private Integer id;
    @Setter
    private String observacao;
    private final List<Contato> lista;

    public ContatosImpl() {
        this.lista = new ArrayList<>();
    }

    public ContatosImpl(final Integer id, final String observacao) {
        this.id = id;
        this.observacao = observacao;
        this.lista = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getObservacao() {
        return this.observacao;
    }

    @Override
    public List<Contato> getLista() {
        return Collections.unmodifiableList(this.lista);
    }

    @Override
    public void add(final Contato contato) {
        this.lista.add(contato);
    }

    @Override
    public Email getEmailAt(final int index) {
        return (Email) this.lista.get(index);
    }

    @Override
    public Celular getCelularAt(final int index) {
        return (Celular) this.lista.get(index);
    }

    @Override
    public Telefone getTelefoneAt(final int index) {
        return (Telefone) this.lista.get(index);
    }

    @Override
    public Site getSiteAt(final int index) {
        return (Site) this.lista.get(index);
    }

    @Override
    public OutroContato getOutroAt(final int index) {
        return (OutroContato) this.lista.get(index);
    }
}
