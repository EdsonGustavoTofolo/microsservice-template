package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edsongustavotofolo.microservicetemplate.domain.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.ControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorRequestModel;
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

import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.CreateFornecedorRequestModelBuilder.umFornecedor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
    void naoDeveCriarFornecedorComCnpjInvalido() throws Exception {
        var requestModel = umFornecedor().comCnpj("99999999999999").get();

        var expectedMessage = "CNPJ Inválido!";
        var ex = new BusinessRuleException(expectedMessage);
        doThrow(ex).when(criarFornecedorInputBoundary).execute(requestModel);

        ResultActions perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(requestModel)));

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.mensagens").isArray())
                .andExpect(jsonPath("$.mensagens[0]").value(expectedMessage))
                .andDo(print());
    }

    @Test
    void naoDeveCriarFornecedorSemOsCamposObrigatorios() throws Exception {
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
    void deveCriarFornecedorComSucesso() throws Exception {
        // cenario
        var responseModel = new NovoFornecedorResponseModel(1);
        Mockito.when(criarFornecedorInputBoundary
                .execute(any(CreateFornecedorRequestModel.class)))
                .thenReturn(responseModel);

        var fornecedorRequestModel = CreateFornecedorRequestModel.builder()
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
                                CreateContatoModel.builder()
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