package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

public interface EnderecoRequest {
    String getLogradouro();
    String getNumero();
    String getBairro();
    String getComplemento();
    String getPontoDeReferencia();
    String getCep();
    Integer getCidade();
    String getUf();
    boolean hasCity();
}
