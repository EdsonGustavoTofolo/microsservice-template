package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContatoModel {
    private TipoDeContatoEnum tipoDeContato;
    private String ddd;
    private String numero;
    private String enderecoEmail;
    private String urlSite;
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
