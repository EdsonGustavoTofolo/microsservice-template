package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFornecedorModel {
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
    private List<CreateContatoModel> contatos;
    private String observacaoContatos;
}
