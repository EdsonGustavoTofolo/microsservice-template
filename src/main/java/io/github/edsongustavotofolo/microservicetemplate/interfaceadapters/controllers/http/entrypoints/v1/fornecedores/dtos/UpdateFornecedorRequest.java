package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Value
public class UpdateFornecedorRequest {
    @NotEmpty(message = "CNPJ: Campo obrigatório")
    @Size(message = "Informe o CNPJ sem formatação, com somente {max} digitos", min = 14, max = 14)
    String cnpj;
    @NotEmpty(message = "Razão Social: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String razaoSocial;
    @NotEmpty(message = "Nome Fantasia: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String nomeFantasia;
    String observacao;
    @NotEmpty(message = "Logradouro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String logradouro;
    @NotEmpty(message = "Número: Campo obrigatório. Caso nao exista infomar 'SN'")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 10)
    String numero;
    @NotEmpty(message = "Bairro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 60)
    String bairro;
    @Size(message = "Complemento: Tamanho máximo de {max} caracteres", max = 100)
    String complemento;
    @Size(message = "Ponto de referência: Tamanho máximo de {max} caracteres", max = 100)
    String pontoDeReferencia;
    @NotEmpty(message = "CEP: Campo obrigatório")
    @Size(message = "CEP: Tamanho máximo de {max} caracteres", max = 8)
    String cep;
    @NotNull(message = "Cidade: Campo obrigatório")
    Integer cidadeId;
    @NotEmpty(message = "Contatos: Campo obrigatório")
    List<@Valid UpdateContatoRequest> contatos;
    String observacaoContatos;
}
