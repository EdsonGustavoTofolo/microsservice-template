package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class UpdateFornecedor {
    private final String cnpj;
    private final String razaoSocial;
    private final String nomeFantasia;
    private final String observacao;
    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final String complemento;
    private final String pontoDeReferencia;
    private final String cep;
    private final Integer cidadeId;
    private final List<UpdateContato> contatos;
    private final String observacaoContatos;
}
