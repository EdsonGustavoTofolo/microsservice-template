package io.github.edsongustavotofolo.microservicetemplate.domain.entities.impl;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatosImpl implements Contatos {
    private Integer id;
    @Setter
    private String observacao;
    private List<Contato> lista;

    public ContatosImpl() {
        this.lista = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getObservacao() {
        return observacao;
    }

    @Override
    public List<Contato> getLista() {
        return Collections.unmodifiableList(lista);
    }

    @Override
    public void adicionar(final Contato contato) {
        lista.add(contato);
    }

    @Override
    public Email getEmailAt(int index) {
        return (Email) this.lista.get(index);
    }

    @Override
    public Celular getCelularAt(int index) {
        return (Celular) this.lista.get(index);
    }

    @Override
    public Telefone getTelefoneAt(int index) {
        return (Telefone) this.lista.get(index);
    }

    @Override
    public Site getSiteAt(int index) {
        return (Site) this.lista.get(index);
    }

    @Override
    public OutroContato getOutroAt(int index) {
        return (OutroContato) this.lista.get(index);
    }
}
