package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public final class UpdateFornecedorRequest {
    @NotEmpty(message = "CNPJ: Campo obrigatório")
    @Size(message = "Informe o CNPJ sem formatação, com somente {max} digitos", min = 14, max = 14)
    private final String cnpj;
    @NotEmpty(message = "Razão Social: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private final String razaoSocial;
    @NotEmpty(message = "Nome Fantasia: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private final String nomeFantasia;
    private final String observacao;
    @NotEmpty(message = "Logradouro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 255)
    private final String logradouro;
    @NotEmpty(message = "Número: Campo obrigatório. Caso nao exista infomar 'SN'")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 10)
    private final String numero;
    @NotEmpty(message = "Bairro: Campo obrigatório")
    @Size(message = "Tamanho máximo de {max} caracteres", max = 60)
    private final String bairro;
    @Size(message = "Complemento: Tamanho máximo de {max} caracteres", max = 100)
    private final String complemento;
    @Size(message = "Ponto de referência: Tamanho máximo de {max} caracteres", max = 100)
    private final String pontoDeReferencia;
    @NotEmpty(message = "CEP: Campo obrigatório")
    @Size(message = "CEP: Tamanho máximo de {max} caracteres", max = 8)
    private final String cep;
    @NotNull(message = "Cidade: Campo obrigatório")
    private final Integer cidadeId;
    @NotEmpty(message = "Contatos: Campo obrigatório")
    private final List<@Valid UpdateContatoRequest> contatos;
    private final String observacaoContatos;
}
