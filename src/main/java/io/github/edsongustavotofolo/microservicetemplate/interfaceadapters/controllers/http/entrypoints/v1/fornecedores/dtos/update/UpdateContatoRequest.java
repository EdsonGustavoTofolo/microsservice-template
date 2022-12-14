package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.update;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Conditional;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Enum;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Value
@Conditional(selected = "tipoDeContato", values = {"TELEFONE", "CELULAR"}, required = {"ddd", "numero"})
@Conditional(selected = "tipoDeContato", values = "EMAIL", required = "enderecoEmail")
@Conditional(selected = "tipoDeContato", values = "SITE", required = "urlSite")
@Conditional(selected = "tipoDeContato", values = "OUTRO", required = "texto")
public class UpdateContatoRequest {
    Integer id;
    @NotNull(message = "{fields.required}")
    @Schema(implementation = TipoDeContatoEnum.class)
    @Enum(enumClass = TipoDeContatoEnum.class)
    String tipoDeContato;
    @Size(message = "{fields.fixedLength}", min = 2, max = 2)
    String ddd;
    @Size(message = "{fields.maxLength}", max = 10)
    String numero;
    @Email
    @Size(message = "{fields.maxLength}", max = 255)
    String enderecoEmail;
    @Size(message = "{fields.maxLength}", max = 255)
    String urlSite;
    @Size(message = "{fields.maxLength}", max = 255)
    String texto;

    public static UpdateContatoRequest outro(final Integer id, final String texto) {
        return UpdateContatoRequest.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.OUTRO.name())
                .texto(texto)
                .build();
    }

    public static UpdateContatoRequest site(final Integer id, final String url) {
        return UpdateContatoRequest.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.SITE.name())
                .urlSite(url)
                .build();
    }

    public static UpdateContatoRequest celular(final Integer id, final String ddd, final String numero) {
        return UpdateContatoRequest.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.CELULAR.name())
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static UpdateContatoRequest telefone(final Integer id, final String ddd, final String numero) {
        return UpdateContatoRequest.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.TELEFONE.name())
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static UpdateContatoRequest email(final Integer id, final String enderecoEmail) {
        return UpdateContatoRequest.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.EMAIL.name())
                .enderecoEmail(enderecoEmail)
                .build();
    }
}
