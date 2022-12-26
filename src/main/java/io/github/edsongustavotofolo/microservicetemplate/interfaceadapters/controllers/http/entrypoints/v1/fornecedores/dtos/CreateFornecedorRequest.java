package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.ExistsCountry;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Value
public class CreateFornecedorRequest {
    @NotBlank(message = "Campo obrigatório")
    @Size(message = "Informe o CNPJ sem formatação, com somente {max} digitos", min = 14, max = 14)
    String cnpj;
    @NotBlank(message = "Razão Social: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String razaoSocial;
    @NotBlank(message = "Nome Fantasia: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String nomeFantasia;
    String observacao;
    @NotBlank(message = "Logradouro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    String logradouro;
    @NotBlank(message = "Número: Campo obrigatório. Caso nao exista infomar 'SN'")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 10)
    String numero;
    @NotBlank(message = "Bairro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 60)
    String bairro;
    @Size(message = "Complemento: Tamanho máximo de {max} caracteres", max = 100)
    String complemento;
    @Size(message = "Ponto de referência: Tamanho máximo de {max} caracteres", max = 100)
    String pontoDeReferencia;
    @NotBlank(message = "CEP: Campo obrigatório")
    @Size(message = "CEP: Deve possuir tamanho de {max} caracteres", min = 8, max = 8)
    String cep;
    @NotNull(message = "Cidade: Campo obrigatório")
    @ExistsCountry
    Integer cidade;
    @NotEmpty(message = "Contatos: Campo obrigatório")
    List<@Valid CreateContatoRequest> contatos;
    String observacaoContatos;
}
