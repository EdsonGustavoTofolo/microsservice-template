package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.converters.CreateFornecedorConverter;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.CreateFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.UpdateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.AtualizarFornecedorInputBoundary;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CreateFornecedorInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/fornecedores")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fornecedor", description = "Cadastro de Fornecedor")
public class FornecedorController {

    private final CreateFornecedorInputPort createFornecedorInputPort;
    private final AtualizarFornecedorInputBoundary atualizarFornecedorInputBoundary;

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
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody CreateFornecedorRequestModel requestModel) {
        final var model = CreateFornecedorConverter.toModel(requestModel);

        var id = this.createFornecedorInputPort.execute(model);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.get())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Atualiza um fornecedor",
            description = "Atualiza o fornecedor com o ID passado na URL e com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Dados do Fornecedor inválido"),
            @ApiResponse(responseCode = "404",
                    description = "Fornecedor não encontrado")
    })
    @PatchMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patch(@PathVariable Integer id, @Valid @RequestBody UpdateFornecedorModel requestModel) {
        this.atualizarFornecedorInputBoundary.execute(id, requestModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
