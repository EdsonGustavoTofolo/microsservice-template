package io.github.edsongustavotofolo.microservicetemplate.usecases.models;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.annotations.Conditional;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Conditional(selected = "tipoDeContato", values = {"TELEFONE", "CELULAR"}, required = {"ddd", "numero"}, message = "Informe o DDD e o Numero do contato telefonico")
@Conditional(selected = "tipoDeContato", values = "EMAIL", required = "enderecoEmail", message = "Informe o endereco de e-mail")
@Conditional(selected = "tipoDeContato", values = "SITE", required = "urlSite", message = "Informe uma URL valida")
@Conditional(selected = "tipoDeContato", values = "OUTRO", required = "texto", message = "Informe um texto")
public class CreateContatoModel {
    @NotNull(message = "Tipo de Contato deve ser informado")
    private TipoDeContatoEnum tipoDeContato;
    @Size(message = "DDD: tamanho máximo de {max} digitos", min = 2, max = 2)
    private String ddd;
    @Size(message = "Número: tamanho maximo de {max} digitos", max = 10)
    private String numero;
    @Size(message = "Endereço de e-mail: tamanho máximo de {max} caracteres", max = 255)
    private String enderecoEmail;
    @Size(message = "URL: tamanho máximo de {max} caracteres", max = 255)
    private String urlSite;
    @Size(message = "Texto: tamanho máximo de {max} caracteres", max = 255)
    private String texto;

    public static CreateContatoModel outro(final String texto) {
        return CreateContatoModel.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static CreateContatoModel site(final String url) {
        return CreateContatoModel.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static CreateContatoModel celular(final String ddd, final String numero) {
        return CreateContatoModel.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static CreateContatoModel telefone(final String ddd, final String numero) {
        return CreateContatoModel.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static CreateContatoModel email(final String enderecoEmail) {
        return CreateContatoModel.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(enderecoEmail)
                .build();
    }
}
