package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades.dtos.GetCidadesByNameResponse;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades.services.GetCidadesByName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/cidades")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cidade", description = "Informações sobre Cidades")
public class CidadeController {

    private final GetCidadesByName getCidadesByName;

    @Operation(summary = "Retorna lista cidades que contém o nome informado",
            description = "Busca as cidades cadastradas pelo nome desejado")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/{nome}")
    public ResponseEntity<List<GetCidadesByNameResponse>> getCidadesByName(@PathVariable final String nome) {
        final var cidades = this.getCidadesByName.execute(nome);
        return ResponseEntity.ok(cidades);
    }
}
