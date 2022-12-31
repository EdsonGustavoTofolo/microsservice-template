package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class UpdateContato {
    Integer id;
    TipoDeContatoEnum tipoDeContato;
    String ddd;
    String numero;
    String enderecoEmail;
    String urlSite;
    String texto;

    public static UpdateContato outro(final String texto) {
        return UpdateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static UpdateContato site(final String url) {
        return UpdateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static UpdateContato celular(final String ddd, final String numero) {
        return UpdateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static UpdateContato telefone(final String ddd, final String numero) {
        return UpdateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static UpdateContato email(final String enderecoEmail) {
        return UpdateContato.builder()
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(enderecoEmail)
                .build();
    }}
