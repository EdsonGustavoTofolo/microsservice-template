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
}
