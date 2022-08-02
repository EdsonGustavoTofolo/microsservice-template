package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CriarFornecedorInputBoundary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/fornecedores", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fornecedor", description = "Cadastro de Fornecedor")
public class FornecedorRestController {

    private final CriarFornecedorInputBoundary criarFornecedorInputBoundary;

    @Operation(summary = "Cria um novo fornecedor",
            description = "Cria um fornecedor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Criado com sucesso",
                    headers = @Header(name = LOCATION,
                            description = "Retorna a URL do Fornecedor criado",
                            schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400",
                    description = "Dados do fornecedor inválidos")
    })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CriarFornecedorRequestModel requestModel) {
        var id = this.criarFornecedorInputBoundary.execute(requestModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.get())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
