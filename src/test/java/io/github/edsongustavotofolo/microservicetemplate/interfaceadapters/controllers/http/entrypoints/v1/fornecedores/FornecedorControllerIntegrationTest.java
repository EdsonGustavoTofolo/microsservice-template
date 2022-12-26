package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

import io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.bundles.MessageSourceConfig;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.BaseControllerTest;
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

import java.util.List;
import java.util.stream.Stream;

import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.BASE_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequest;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComCnpj;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures.CreateFornecedorRequestFixture.umFornecedorRequestComNomeFantasia;
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

                Arguments.of(true, ACCEPT_LANGUAGE_PT_BR, umFornecedorRequestComNomeFantasia("Nome".repeat(64)), "nomeFantasia", List.of("tamanho máximo de 255 caracteres")),
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
                        .andExpect(jsonPath("$.error.fields." + fieldName).isArray());

        messages.forEach(message -> {
            try {
                perform.andExpect(jsonPath("$.error.fields." + fieldName, hasItem(message)));
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