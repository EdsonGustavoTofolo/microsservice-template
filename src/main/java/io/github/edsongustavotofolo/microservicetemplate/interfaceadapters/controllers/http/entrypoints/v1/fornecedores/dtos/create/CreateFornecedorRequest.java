package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.create;

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
    @Size(message = "{fornecedor.fields.cnpj}", min = 14, max = 14)
    String cnpj;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String razaoSocial;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String nomeFantasia;
    String observacao;
    @Valid
    @NotNull(message = "{fields.required}")
    CreateEnderecoRequest endereco;
    @NotEmpty(message = "{fields.required}")
    List<@Valid CreateContatoRequest> contatos;
    String observacaoContatos;
}
