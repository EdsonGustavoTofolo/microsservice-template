package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;

public interface Fornecedor {
    Integer getId();
    Cnpj getCnpj();
    String getRazaoSocial();
    String getNomeFantasia();
    String getObservacao();
    Endereco getEndereco();
    Contatos getContatos();
}
