package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.enums.TipoDeContatoEnum;
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

    public static UpdateContato outro(final Integer id, final String texto) {
        return UpdateContato.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                .texto(texto)
                .build();
    }

    public static UpdateContato site(final Integer id, final String url) {
        return UpdateContato.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.SITE)
                .urlSite(url)
                .build();
    }

    public static UpdateContato celular(final Integer id, final String ddd, final String numero) {
        return UpdateContato.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                .ddd(ddd)
                .numero(numero)
                .build();
    }

    public static UpdateContato telefone(final Integer id, final String ddd, final String numero) {
        return UpdateContato.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                .ddd(ddd)
                .numero(numero)
                .build();
    }
    public static UpdateContato email(final Integer id, final String enderecoEmail) {
        return UpdateContato.builder()
                .id(id)
                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                .enderecoEmail(enderecoEmail)
                .build();
    }}
