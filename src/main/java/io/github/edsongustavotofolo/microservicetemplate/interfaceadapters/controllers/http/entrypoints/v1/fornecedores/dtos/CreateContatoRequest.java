package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Conditional;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Value
@Conditional(selected = "tipoDeContato", values = {"TELEFONE", "CELULAR"}, required = {"ddd", "numero"}, message = "Informe o DDD e o Numero do contato telefonico")
@Conditional(selected = "tipoDeContato", values = "EMAIL", required = "enderecoEmail", message = "Informe o endereco de e-mail")
@Conditional(selected = "tipoDeContato", values = "SITE", required = "urlSite", message = "Informe uma URL valida")
@Conditional(selected = "tipoDeContato", values = "OUTRO", required = "texto", message = "Informe um texto")
public class CreateContatoRequest {
    @NotNull(message = "{fields.required}")
    TipoDeContatoEnum tipoDeContato;
    @Size(message = "{fields.maxLength}", min = 2, max = 2)
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
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static CreateContatoRequest site(final String url) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static CreateContatoRequest celular(final String ddd, final String numero) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static CreateContatoRequest telefone(final String ddd, final String numero) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static CreateContatoRequest email(final String enderecoEmail) {
        return CreateContatoRequest.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(enderecoEmail)
                .build();
    }
}
