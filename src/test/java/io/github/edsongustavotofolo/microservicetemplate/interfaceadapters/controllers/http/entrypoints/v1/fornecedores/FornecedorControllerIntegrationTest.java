package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.bundles.MessageSourceConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.BaseControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.ControllerExceptionHandler;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients.ViaCepClient;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.enums.ErrorType;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.CidadeBuilder.umaCidade;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.BASE_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.UPDATE_FORNECEDOR_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.getFullPath;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.CreateContatoRequestBuilder.createContatoRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.CreateEnderecoRequestBuilder.createEnderecoRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.CreateFornecedorRequestBuilder.createFornecedorRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.UpdateFornecedorRequestBuilder.updateFornecedorRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateway.clients.builders.ViaCepResponseBuilder.umViaCepResponse;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {MessageSourceConfig.class, ControllerExceptionHandler.class, FornecedorController.class, CreateFornecedor.class, UpdateFornecedor.class})
@WebMvcTest
class FornecedorControllerIntegrationTest extends BaseControllerTest {

    private static final String ACCEPT_LANGUAGE_PT_BR = "pt-BR";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateFornecedor createFornecedor;
    @MockBean
    private UpdateFornecedor updateFornecedor;

    @MockBean
    private CidadeProvider cidadeProvider;
    @MockBean
    private ViaCepClient cepClient;

    @Captor
    private ArgumentCaptor<CreateFornecedorRequest> createFornecedorRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<UpdateFornecedorRequest> updateFornecedorRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idArgumentCaptor;

    private static Stream<Arguments> provideInvalidContatosCreateFornecedorBody() {
        return Stream.of(
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(null).build(), "contatos", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(new ArrayList<>()).build(), "contatos", List.of("campo obrigatório")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(createContatoRequest().tipoDeContato(null).build())).build(), "contatos[0].tipoDeContato", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(createContatoRequest().tipoDeContato("XPTO").build())).build(), "contatos[0].tipoDeContato", List.of("valor inválido")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(createContatoRequest().tipoDeContato("").build())).build(), "contatos[0].tipoDeContato", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(createContatoRequest().tipoDeContato(" ").build())).build(), "contatos[0].tipoDeContato", List.of("campo obrigatório")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular(null, "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular(" ", "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("", "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("1", "123"))).build(), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("123", "123"))).build(), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("46", null))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("46", ""))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("46", " "))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.celular("46", "12345678901"))).build(), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone(null, "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone(" ", "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("", "123"))).build(), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("1", "123"))).build(), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("123", "123"))).build(), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("46", null))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("46", ""))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("46", " "))).build(), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.telefone("46", "12345678901"))).build(), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.email(null))).build(), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.email(""))).build(), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.email(" "))).build(), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.email("email".repeat(52)))).build(), "contatos[0].enderecoEmail", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.site(null))).build(), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.site(""))).build(), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.site(" "))).build(), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.site("www".repeat(86)))).build(), "contatos[0].urlSite", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.outro(null))).build(), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.outro(""))).build(), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.outro(" "))).build(), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().contatos(List.of(CreateContatoRequest.outro("www".repeat(86)))).build(), "contatos[0].texto", List.of("tamanho máximo de 255 caracteres"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidContatosCreateFornecedorBody")
    void shouldResponseWithBadRequestWhenCreateFornecedorIsCalledWithInvalidContatos(final String language,
                                                                                     final CreateFornecedorRequest request,
                                                                                     final String fieldName,
                                                                                     final List<String> messages) throws Exception {
        this.requestToCreateFornecedor(true, language, request, fieldName, messages);
    }

    private static Stream<Arguments> provideInvalidFornecedorDataCreateFornecedorBody() {
        return Stream.of(
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().cnpj("").build(), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().cnpj(null).build(), "cnpj", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().cnpj(" ").build(), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().cnpj("123456789").build(), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().cnpj("1234567890123456").build(), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().razaoSocial("").build(), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().razaoSocial(" ").build(), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().razaoSocial(null).build(), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().razaoSocial("Nome".repeat(64)).build(), "razaoSocial", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().nomeFantasia("").build(), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().nomeFantasia(" ").build(), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().nomeFantasia(null).build(), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().nomeFantasia("Nome".repeat(64)).build(), "nomeFantasia", List.of("tamanho máximo de 255 caracteres"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFornecedorDataCreateFornecedorBody")
    void shouldResponseWithBadRequestWhenCreateFornecedorIsCalledWithInvalidFornecedorData(final String language,
                                                                                           final CreateFornecedorRequest request,
                                                                                           final String fieldName,
                                                                                           final List<String> messages) throws Exception {
        this.requestToCreateFornecedor(true, language, request, fieldName, messages);
    }

    private static Stream<Arguments> provideInvalidEnderecoCreateFornecedorBody() {
        return Stream.of(
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(null).build(), "endereco", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().logradouro("").build()).build(), "endereco.logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().logradouro(" ").build()).build(), "endereco.logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().logradouro(null).build()).build(), "endereco.logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().logradouro("Nome".repeat(64)).build()).build(), "endereco.logradouro", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().numero("").build()).build(), "endereco.numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().numero(" ").build()).build(), "endereco.numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().numero(null).build()).build(), "endereco.numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().numero("SN".repeat(6)).build()).build(), "endereco.numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().bairro("").build()).build(), "endereco.bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().bairro(" ").build()).build(), "endereco.bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().bairro(null).build()).build(), "endereco.bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().bairro("Nome".repeat(16)).build()).build(), "endereco.bairro", List.of("tamanho máximo de 60 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().complemento("Nome".repeat(26)).build()).build(), "endereco.complemento", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().pontoDeReferencia("Nome".repeat(26)).build()).build(), "endereco.pontoDeReferencia", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cep("").build()).build(), "endereco.cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cep(" ").build()).build(), "endereco.cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cep(null).build()).build(), "endereco.cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cep("cep".repeat(3)).build()).build(), "endereco.cep", List.of("tamanho máximo de 8 caracteres")),

                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cidade(null).build()).build(), "endereco.cidade", List.of("campo obrigatório")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cidade(0).build()).build(), "endereco.cidade", List.of("valor inválido")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequest().endereco(createEnderecoRequest().cidade(123).build()).build(), "endereco.cidade", List.of("valor inválido"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEnderecoCreateFornecedorBody")
    void shouldResponseWithBadRequestWhenCreateFornecedorIsCalledWithInvalidEndereco(final boolean existsCidade,
                                                                                     final String language,
                                                                                     final CreateFornecedorRequest request,
                                                                                     final String fieldName,
                                                                                     final List<String> messages) throws Exception {
        this.requestToCreateFornecedor(existsCidade, language, request, fieldName, messages);
    }


    private void requestToCreateFornecedor(final boolean existsCidade,
                                           final String language,
                                           final CreateFornecedorRequest request,
                                           final String fieldName,
                                           final List<String> messages) throws Exception {
        when(this.cidadeProvider.existsById(any())).thenReturn(existsCidade);
        when(this.cidadeProvider.getById(any())).thenReturn(Optional.of(umaCidade().build()));
        when(this.cepClient.buscarPorCep(anyString())).thenReturn(Optional.of(umViaCepResponse().build()));

        final var perform = this.mockMvc.perform(
                post(BASE_PATH)
                        .header(HttpHeaders.ACCEPT_LANGUAGE, language)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapToJson(request)));

        perform
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error.code").value(ErrorType.EXPT001.name()))
                .andExpect(jsonPath("$.error.fields").isArray())
                .andExpect(jsonPath("$.error.fields[0].field").value(fieldName))
                .andExpect(jsonPath("$.error.fields[0].messages").isArray());

        messages.forEach(message -> {
            try {
                perform.andExpect(jsonPath("$.error.fields[0].messages", hasItem(message)));
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        });

        verifyNoInteractions(this.createFornecedor);
    }

    @Test
    void shouldCreateFornecedorSuccessfully() throws Exception {
        when(this.cidadeProvider.existsById(any())).thenReturn(true);
        when(this.cidadeProvider.getById(any())).thenReturn(Optional.of(umaCidade().build()));
        when(this.cepClient.buscarPorCep(anyString())).thenReturn(Optional.of(umViaCepResponse().build()));
        when(this.createFornecedor.execute(any())).thenReturn(1);

        // execucao
        final var perform = this.mockMvc.perform(
                post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapToJson(createFornecedorRequest().build())));

        // verificacao
        perform
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/microservice-template/v1/fornecedores/1"));

        verify(this.createFornecedor).execute(this.createFornecedorRequestArgumentCaptor.capture());

        final var request = this.createFornecedorRequestArgumentCaptor.getValue();

        assertNotNull(request);
    }

    @Test
    void shouldUpdateFornecedorSuccessfully() throws Exception {
        when(this.cidadeProvider.existsById(any())).thenReturn(true);

        // execucao
        final var perform = this.mockMvc.perform(
                put(getFullPath(UPDATE_FORNECEDOR_PATH), 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapToJson(updateFornecedorRequest().build())));

        // verificacao
        perform.andExpect(status().isOk());

        verify(this.updateFornecedor).execute(this.idArgumentCaptor.capture(), this.updateFornecedorRequestArgumentCaptor.capture());

        final var id = this.idArgumentCaptor.getValue();

        assertEquals(1, id);

        final var request = this.updateFornecedorRequestArgumentCaptor.getValue();

        assertNotNull(request);
    }
}