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

    void setId(final Integer id);

    void setCnpj(final Cnpj cnpj);

    void setRazaoSocial(final String razaoSocial);

    void setNomeFantasia(final String nomeFantasia);

    void setObservacao(final String observacao);

    void setEndereco(final String logradouro,
                     final String numero,
                     final String bairro,
                     final String complemento,
                     final String pontoDeReferencia,
                     final String cep,
                     final Integer cidadeId);
}
