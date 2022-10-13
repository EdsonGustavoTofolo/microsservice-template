package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class GetCidadesByNameResponse {
    Integer id;
    String nome;
    Integer estadoId;
    String estadoNome;
    Integer paisId;
    String paisNome;
}
