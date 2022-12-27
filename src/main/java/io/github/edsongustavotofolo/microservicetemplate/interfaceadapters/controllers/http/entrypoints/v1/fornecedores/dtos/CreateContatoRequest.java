package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Conditional;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Enum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Value
@Conditional(selected = "tipoDeContato", values = {"TELEFONE", "CELULAR"}, required = {"ddd", "numero"})
@Conditional(selected = "tipoDeContato", values = "EMAIL", required = "enderecoEmail")
@Conditional(selected = "tipoDeContato", values = "SITE", required = "urlSite")
@Conditional(selected = "tipoDeContato", values = "OUTRO", required = "texto")
public class CreateContatoRequest {
    @NotBlank(message = "{fields.required}")
    @Schema(implementation = TipoDeContatoEnum.class)
    @Enum(enumClass = TipoDeContatoEnum.class)
    String tipoDeContato;
    @Size(message = "{fields.fixedLength}", min = 2, max = 2)
    String ddd;
    @Size(message = "{fields.maxLength}", max = 10)
    String numero;
    @Size(message = "{fields.maxLength}", max = 255)
    String enderecoEmail;
    @Size(message = "{fields.maxLength}", max = 255)
    String urlSite;
    @Size(message = "{fields.maxLength}", max = 255)
    String texto;

    public static CreateContatoRequest outro(final String texto) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO.name())
                .texto(texto)
                .build();
    }

    public static CreateContatoRequest site(final String url) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE.name())
                .urlSite(url)
                .build();
    }

    public static CreateContatoRequest celular(final String ddd, final String numero) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR.name())
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static CreateContatoRequest telefone(final String ddd, final String numero) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE.name())
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static CreateContatoRequest email(final String enderecoEmail) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL.name())
                .enderecoEmail(enderecoEmail)
                .build();
    }
}
