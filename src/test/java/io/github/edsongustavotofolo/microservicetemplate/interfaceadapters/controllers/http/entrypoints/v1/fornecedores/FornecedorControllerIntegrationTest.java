package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.BaseControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.FornecedorControllerPaths.BASE_PATH;
import static io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builder.CreateFornecedorRequestBuilder.umFornecedorRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {FornecedorController.class, CreateFornecedor.class, UpdateFornecedor.class})
@WebMvcTest
class FornecedorControllerIntegrationTest extends BaseControllerTest {

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

    @Test
    void shouldCreateFornecedorSuccessfully() throws Exception {
        when(this.cidadeProvider.existsById(any())).thenReturn(true);
        when(this.createFornecedor.execute(any())).thenReturn(1);

        // execucao
        final var perform = this.mockMvc.perform(
                post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapToJson(umFornecedorRequest().get())));

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