package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class UpdateContato {
    private final Integer id;
    private final TipoDeContatoEnum tipoDeContato;
    private final String ddd;
    private final String numero;
    private final String enderecoEmail;
    private final String urlSite;
    private final String texto;
}
