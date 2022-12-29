package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Endereco;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Size(message = "{fields.maxLength}", min = 8, max = 8)
    String cep;
    @NotNull(message = "{fields.required}")
    Integer cidade;
    @NotBlank(message = "{fields.required}")
    @Size(message = "{fields.maxLength}", max = 2)
    String uf;

    public boolean hasCity() {
        return Objects.nonNull(this.cidade) && this.cidade > 0;
    }
}
