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
    @NotBlank(message = "{fields.required}")
    @Size(message = "{createfornecedor.fields.cnpj}", min = 14, max = 14)
    String cnpj;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String razaoSocial;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String nomeFantasia;
    String observacao;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String logradouro;
    @NotBlank(message = "{createfornecedor.fields.numero}")
    @Size(message = "{fields.maxLength}", max = 10)
    String numero;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 60)
    String bairro;
    @Size(message = "{fields.maxLength}", max = 100)
    String complemento;
    @Size(message = "{fields.maxLength}", max = 100)
    String pontoDeReferencia;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", min = 8, max = 8)
    String cep;
    @NotNull(message = "{fields.required}")
    @ExistsCountry
    Integer cidade;
    @NotEmpty(message = "{fields.required}")
    List<@Valid CreateContatoRequest> contatos;
    String observacaoContatos;
}
