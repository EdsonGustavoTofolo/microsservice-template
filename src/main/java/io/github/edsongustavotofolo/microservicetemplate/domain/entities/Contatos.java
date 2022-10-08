package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import java.util.List;

public interface Contatos {
    Integer getId();

    String getObservacao();

    List<Contato> getLista();

    void add(final Contato contato);

    Email getEmailAt(int index);

    Celular getCelularAt(int index);

    Telefone getTelefoneAt(int index);

    Site getSiteAt(int index);

    OutroContato getOutroAt(int index);
}
