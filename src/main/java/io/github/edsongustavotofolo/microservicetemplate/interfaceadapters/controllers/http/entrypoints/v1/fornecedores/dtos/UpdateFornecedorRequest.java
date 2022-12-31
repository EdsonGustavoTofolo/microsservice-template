package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

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
public class UpdateFornecedorRequest {
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String razaoSocial;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String nomeFantasia;
    String observacao;
    @NotNull(message = "{fields.required}")
    UpdateEnderecoRequest endereco;
    @NotEmpty(message = "{fields.required}")
    List<@Valid UpdateContatoRequest> contatos;
    String observacaoContatos;
}
