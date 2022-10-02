package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFornecedor {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String observacao;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String pontoDeReferencia;
    private String cep;
    private Integer cidadeId;
    private List<CreateContato> contatos;
    private String observacaoContatos;
}
