package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cep;

public interface Endereco {
    Integer getId();
    String getLogradouro();
    String getNumero();
    String getBairro();
    String getComplemento();
    String getPontoDeReferencia();
    Cep getCep();
    Cidade getCidade();
}
