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
public final class ContatoModel {
    @NotNull(message = "Tipo de Contato deve ser informado")
    private final TipoDeContatoEnum tipoDeContato;
    @Size(message = "DDD: tamanho máximo de {max} digitos", min = 2, max = 2)
    private final String ddd;
    @Size(message = "Número: tamanho maximo de {max} digitos", max = 10)
    private final String numero;
    @Size(message = "Endereço de e-mail: tamanho máximo de {max} caracteres", max = 255)
    private final String enderecoEmail;
    @Size(message = "URL: tamanho máximo de {max} caracteres", max = 255)
    private final String urlSite;
    @Size(message = "Texto: tamanho máximo de {max} caracteres", max = 255)
    private final String texto;
}
