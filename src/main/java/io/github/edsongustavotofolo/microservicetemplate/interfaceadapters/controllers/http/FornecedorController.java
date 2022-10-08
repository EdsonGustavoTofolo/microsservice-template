package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.services.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/v1/fornecedores")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Fornecedor", description = "Cadastro de Fornecedor")
public class FornecedorController {

    private final CreateFornecedor createFornecedor;
    private final UpdateFornecedor updateFornecedor;

    @Operation(summary = "Cria um novo fornecedor",
            description = "Cria um fornecedor com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Criado com sucesso",
                    headers = @Header(name = LOCATION,
                            description = "Retorna a URL do Fornecedor criado",
                            schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400",
                    description = "Dados do fornecedor inválidos\t\n" +
                            "Cnpj inválido"),
            @ApiResponse(responseCode = "422",
                    description = "Fornecedor já existe")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody final CreateFornecedorRequest request) throws BusinessRuleException {
        final var id = this.createFornecedor.execute(request);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
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
    public ResponseEntity<Void> patch(@PathVariable final Integer id,
                                      @Valid @RequestBody final UpdateFornecedorRequest requestModel) throws BusinessRuleException {
        this.updateFornecedor.execute(id, requestModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
