package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.bundles.MessageSourceConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.BaseControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.ControllerExceptionHandler;
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
import java.util.stream.Stream;

import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.BASE_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.UPDATE_FORNECEDOR_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.getFullPath;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateContatoRequestFixture.createContatoRequestComTipoDeContato;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComBairro;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComCep;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComCidade;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComCnpj;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComComplemento;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComContatos;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComLogradouro;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComNomeFantasia;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComNumero;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComPontoDeReferencia;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.createFornecedorRequestComRazaoSocial;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.UpdateFornecedorRequestFixture.updateFornecedorRequest;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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

    @Captor
    private ArgumentCaptor<CreateFornecedorRequest> createFornecedorRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<UpdateFornecedorRequest> updateFornecedorRequestArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idArgumentCaptor;

    private static Stream<Arguments> provideInvalidContatosCreateFornecedorBody() {
        return Stream.of(
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(null), "contatos", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(new ArrayList<>()), "contatos", List.of("campo obrigatório")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(createContatoRequestComTipoDeContato(null))), "contatos[0].tipoDeContato", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(createContatoRequestComTipoDeContato("XPTO"))), "contatos[0].tipoDeContato", List.of("valor inválido")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(createContatoRequestComTipoDeContato(""))), "contatos[0].tipoDeContato", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(createContatoRequestComTipoDeContato(" "))), "contatos[0].tipoDeContato", List.of("campo obrigatório")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular(null, "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular(" ", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("1", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("123", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", null))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", ""))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", " "))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", "12345678901"))), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone(null, "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone(" ", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("1", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("123", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", null))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", ""))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", " "))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", "12345678901"))), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.email(null))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.email(""))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.email(" "))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.email("email".repeat(52)))), "contatos[0].enderecoEmail", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.site(null))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.site(""))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.site(" "))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.site("www".repeat(86)))), "contatos[0].urlSite", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(null))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(""))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(" "))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComContatos(List.of(CreateContatoRequest.outro("www".repeat(86)))), "contatos[0].texto", List.of("tamanho máximo de 255 caracteres"))
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
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCnpj(""), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCnpj(null), "cnpj", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCnpj(" "), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCnpj("123456789"), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCnpj("1234567890123456"), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComRazaoSocial(""), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComRazaoSocial(" "), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComRazaoSocial(null), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComRazaoSocial("Nome".repeat(64)), "razaoSocial", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNomeFantasia(""), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNomeFantasia(" "), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNomeFantasia(null), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNomeFantasia("Nome".repeat(64)), "nomeFantasia", List.of("tamanho máximo de 255 caracteres"))
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
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComLogradouro(""), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComLogradouro(" "), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComLogradouro(null), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComLogradouro("Nome".repeat(64)), "logradouro", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNumero(""), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNumero(" "), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNumero(null), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComNumero("SN".repeat(6)), "numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComBairro(""), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComBairro(" "), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComBairro(null), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComBairro("Nome".repeat(16)), "bairro", List.of("tamanho máximo de 60 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComComplemento("Nome".repeat(26)), "complemento", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComPontoDeReferencia("Nome".repeat(26)), "pontoDeReferencia", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCep(""), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCep(" "), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCep(null), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCep("cep".repeat(3)), "cep", List.of("tamanho máximo de 8 caracteres")),

                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCidade(null), "cidade", List.of("campo obrigatório")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCidade(0), "cidade", List.of("valor inválido")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, createFornecedorRequestComCidade(123), "cidade", List.of("valor inválido"))
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
        when(this.createFornecedor.execute(any())).thenReturn(1);

        // execucao
        final var perform = this.mockMvc.perform(
                post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapToJson(createFornecedorRequest())));

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
                        .content(this.mapToJson(updateFornecedorRequest())));

        // verificacao
        perform.andExpect(status().isOk());

        verify(this.updateFornecedor).execute(this.idArgumentCaptor.capture(), this.updateFornecedorRequestArgumentCaptor.capture());

        final var id = this.idArgumentCaptor.getValue();

        assertEquals(1, id);

        final var request = this.updateFornecedorRequestArgumentCaptor.getValue();

        assertNotNull(request);
    }
}