package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.EnderecoRequest;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Builder
@Value
@Endereco
public class UpdateEnderecoRequest implements EnderecoRequest {
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 255)
    String logradouro;
    @NotBlank(message = "{fornecedor.fields.numero}")
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
    @Pattern(regexp = "^(\\d{8})$", message = "{fields.invalidValue}")
    String cep;
    @NotNull(message = "{fields.required}")
    Integer cidade;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 2)
    String uf;

    public boolean hasCity() {
        return Objects.nonNull(this.cidade);
    }
}
