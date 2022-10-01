package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContatoModel;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class UpdateFornecedorModel {
    @NotEmpty(message = "CNPJ: Campo obrigatório")
    @Size(message = "Informe o CNPJ sem formatação, com somente {max} digitos", min = 14, max = 14)
    private String cnpj;
    @NotEmpty(message = "Razão Social: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String razaoSocial;
    @NotEmpty(message = "Nome Fantasia: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String nomeFantasia;
    private String observacao;
    @NotEmpty(message = "Logradouro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private String logradouro;
    @NotEmpty(message = "Número: Campo obrigatório. Caso nao exista infomar 'SN'")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 10)
    private String numero;
    @NotEmpty(message = "Bairro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 60)
    private String bairro;
    @Size(message = "Complemento: Tamanho máximo de {max} caracteres", max = 100)
    private String complemento;
    @Size(message = "Ponto de referência: Tamanho máximo de {max} caracteres", max = 100)
    private String pontoDeReferencia;
    @NotEmpty(message = "CEP: Campo obrigatório")
    @Size(message = "CEP: Tamanho máximo de {max} caracteres", max = 8)
    private String cep;
    @NotNull(message = "Cidade: Campo obrigatório")
    private Integer cidadeId;
    @NotEmpty(message = "Contatos: Campo obrigatório")
    private List<@Valid UpdateContatoModel> contatos;
    private String observacaoContatos;
}
