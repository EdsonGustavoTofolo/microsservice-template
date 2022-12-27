package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.bundles.MessageSourceConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.BaseControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
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
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateContatoRequestFixture.createContatoRequestComTipoDeContato;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComBairro;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComCep;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComCidade;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComCnpj;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComComplemento;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComContatos;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComLogradouro;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComNomeFantasia;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComNumero;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComPontoDeReferencia;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComRazaoSocial;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    private static Stream<Arguments> provideInvalidCreateFornecedorBody() {
        return Stream.of(
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCnpj(""), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCnpj(null), "cnpj", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCnpj(" "), "cnpj", List.of("campo obrigatório", "informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCnpj("123456789"), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCnpj("1234567890123456"), "cnpj", List.of("informe o CNPJ sem formatação, com somente 14 digitos")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComRazaoSocial(""), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComRazaoSocial(" "), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComRazaoSocial(null), "razaoSocial", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComRazaoSocial("Nome".repeat(64)), "razaoSocial", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNomeFantasia(""), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNomeFantasia(" "), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNomeFantasia(null), "nomeFantasia", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNomeFantasia("Nome".repeat(64)), "nomeFantasia", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComLogradouro(""), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComLogradouro(" "), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComLogradouro(null), "logradouro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComLogradouro("Nome".repeat(64)), "logradouro", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNumero(""), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNumero(" "), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNumero(null), "numero", List.of("campo obrigatório. Caso não exista infomar 'SN'")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNumero("SN".repeat(6)), "numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComBairro(""), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComBairro(" "), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComBairro(null), "bairro", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComBairro("Nome".repeat(16)), "bairro", List.of("tamanho máximo de 60 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComComplemento("Nome".repeat(26)), "complemento", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComPontoDeReferencia("Nome".repeat(26)), "pontoDeReferencia", List.of("tamanho máximo de 100 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCep(""), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCep(" "), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCep(null), "cep", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCep("cep".repeat(3)), "cep", List.of("tamanho máximo de 8 caracteres")),

                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCidade(null), "cidade", List.of("campo obrigatório")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCidade(0), "cidade", List.of("valor inválido")),
                Arguments.of(false, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComCidade(123), "cidade", List.of("valor inválido")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(null), "contatos", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(new ArrayList<>()), "contatos", List.of("campo obrigatório")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(createContatoRequestComTipoDeContato(null))), "contatos[0].tipoDeContato", List.of("campo obrigatório")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular(null, "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular(" ", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("1", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("123", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", null))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", ""))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", " "))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.celular("46", "12345678901"))), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone(null, "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone(" ", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("", "123"))), "contatos[0].ddd", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("1", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("123", "123"))), "contatos[0].ddd", List.of("deve ter 2 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", null))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", ""))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", " "))), "contatos[0].numero", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.telefone("46", "12345678901"))), "contatos[0].numero", List.of("tamanho máximo de 10 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.email(null))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.email(""))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.email(" "))), "contatos[0].enderecoEmail", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.email("email".repeat(52)))), "contatos[0].enderecoEmail", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.site(null))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.site(""))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.site(" "))), "contatos[0].urlSite", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.site("www".repeat(86)))), "contatos[0].urlSite", List.of("tamanho máximo de 255 caracteres")),

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(null))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(""))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.outro(" "))), "contatos[0].texto", List.of("campo obrigatório")),
                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComContatos(List.of(CreateContatoRequest.outro("www".repeat(86)))), "contatos[0].texto", List.of("tamanho máximo de 255 caracteres"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCreateFornecedorBody")
    void shouldResponseWithBadRequestWhenCreateFornecedorIsCalledWithInvalidBody(final boolean existsCidade,
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
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error.code").value(ErrorType.EXPT001.name()))
                .andExpect(jsonPath("$.error.fields").isArray())
                .andExpect(jsonPath("$.error.fields[0].field").value(fieldName))
                .andExpect(jsonPath("$.error.fields[0].messages").isArray());

        messages.forEach(message -> {
            try {
                perform.andExpect(jsonPath("$.error.fields[0].messages", hasItem(message)));
            } catch (Exception e) {
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
                        .content(this.mapToJson(umFornecedorRequest())));

        // verificacao
        perform
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/microservice-template/v1/fornecedores/1"));

        verify(this.createFornecedor).execute(this.createFornecedorRequestArgumentCaptor.capture());

        final var request = this.createFornecedorRequestArgumentCaptor.getValue();

        assertNotNull(request);
    }

    @Test
    void shouldUpdateFornecedorSuccessfully() {
    }
}