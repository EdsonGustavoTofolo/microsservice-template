package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
@Builder
@Value
public class CreateFornecedor {
    String cnpj;
    String razaoSocial;
    String nomeFantasia;
    String observacao;
    String logradouro;
    String numero;
    String bairro;
    String complemento;
    String pontoDeReferencia;
    String cep;
    Integer cidade;
    List<CreateContato> contatos;
    String observacaoContatos;
}
