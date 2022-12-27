package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class CreateContato {
    TipoDeContatoEnum tipoDeContato;
    String ddd;
    String numero;
    String enderecoEmail;
    String urlSite;
    String texto;

    public static CreateContato outro(final String texto) {
        return CreateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static CreateContato site(final String url) {
        return CreateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static CreateContato celular(final String ddd, final String numero) {
        return CreateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static CreateContato telefone(final String ddd, final String numero) {
        return CreateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static CreateContato email(final String enderecoEmail) {
        return CreateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(enderecoEmail)
                .build();
    }
}
