package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.ControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.ContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CriarFornecedorInputBoundary;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FornecedorRestControllerIntegrationTest extends ControllerTest {
    private static final String URL_API_V1_FORNECEDORES = "/api/v1/fornecedores";

    @MockBean
    private CriarFornecedorInputBoundary criarFornecedorInputBoundary;

    @Autowired
    public FornecedorRestControllerIntegrationTest(MockMvc mvc, ObjectMapper mapper) {
        super(mvc, mapper);
    }

    @Test
    public void naoDeveCriarFornecedorSemOsCamposObrigatorios() throws Exception {
        // execucao
        ResultActions perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"));

        // verificacao
        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.mensagens").exists())
                .andExpect(jsonPath("$.mensagens").isArray())
                .andExpect(jsonPath("$.mensagens.length()").value(9))
                .andDo(print());
    }

    @Test
    public void deveCriarFornecedorComSucesso() throws Exception {
        // cenario
        var responseModel = new NovoFornecedorResponseModel(1);
        Mockito.when(criarFornecedorInputBoundary
                .execute(any(CriarFornecedorRequestModel.class)))
                .thenReturn(responseModel);

        var fornecedorRequestModel = CriarFornecedorRequestModel.builder()
                .cnpj("45135006000104")
                .razaoSocial("Fornecedor Ltda")
                .nomeFantasia("Fornecedor & Cia")
                .observacao("falar sempre com JA")
                .logradouro("Rua Bolivia")
                .bairro("Luther King")
                .numero("888")
                .cep("85605410")
                .cidadeId(4108403)
                .complemento("casa da frente")
                .pontoDeReferencia("proximo ao CEMA")
                .contatos(
                        List.of(
                                ContatoModel.builder()
                                        .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                        .enderecoEmail("person@mymail.com")
                                        .build()))
                .observacaoContatos("conversar com representante")
                .build();

        // execucao
        ResultActions perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(fornecedorRequestModel)));

        // verificacao
        perform.andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/fornecedores/1"))
                .andDo(print());
    }
}