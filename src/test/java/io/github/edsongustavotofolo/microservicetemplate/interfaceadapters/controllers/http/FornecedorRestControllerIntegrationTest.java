package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.base.ControllerTest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.handlers.models.ErrorType;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.NovoFornecedorResponseModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.CriarFornecedorInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Locale;

import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.CreateFornecedorRequestModelBuilder.umFornecedor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FornecedorRestControllerIntegrationTest extends ControllerTest {

    private static final String URL_API_V1_FORNECEDORES = "/api/v1/fornecedores";

    @Mock
    private CriarFornecedorInputBoundary criarFornecedorInputBoundary;
    @InjectMocks
    private FornecedorRestController fornecedorRestController;

    @BeforeEach()
    void setup() {
        this.setupBase(this.fornecedorRestController);
    }

    @Test
    void naoDeveCriarFornecedorComCnpjInvalido() throws Exception {
        var requestModel = umFornecedor().comCnpj("99999999999999").get();

        var ex = new FornecedorCnpjInvalidException();
        doThrow(ex).when(criarFornecedorInputBoundary).execute(requestModel);

        ResultActions perform = this.mvc.perform(
                post(URL_API_V1_FORNECEDORES)

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(requestModel)));

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error.message").value(ErrorType.EXPT003.getMessage(Locale.ENGLISH)));
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
                .andExpect(jsonPath("$.error.message").value(ErrorType.EXPT001.getMessage(Locale.ENGLISH)))
                .andExpect(jsonPath("$.error.fields").exists())
                .andExpect(jsonPath("$.error.fields.cidadeId").exists())
                .andExpect(jsonPath("$.error.fields.nomeFantasia").exists())
                .andExpect(jsonPath("$.error.fields.contatos").exists())
                .andExpect(jsonPath("$.error.fields.numero").exists())
                .andExpect(jsonPath("$.error.fields.bairro").exists())
                .andExpect(jsonPath("$.error.fields.logradouro").exists())
                .andExpect(jsonPath("$.error.fields.cnpj").exists())
                .andExpect(jsonPath("$.error.fields.cep").exists());
    }

    @Test
    void deveCriarFornecedorComSucesso() throws Exception {
        // cenario
        var responseModel = new NovoFornecedorResponseModel(1);
        Mockito.when(criarFornecedorInputBoundary
                .execute(any(CreateFornecedorModel.class)))
                .thenReturn(responseModel);

        var fornecedorRequestModel = CreateFornecedorModel.builder()
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