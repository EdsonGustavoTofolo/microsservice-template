package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.CreateFornecedorRequestModelBuilder;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedor;
import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.NovoFornecedorResponseModelBuilder.umFornecedorResponseModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarFornecedorInteractorUnitTest {
    @Mock
    private FornecedorCriadoOutputBoundary presenter;
    @Mock
    private FornecedorDsGateway fornecedorDsGateway;
    @Mock
    private FornecedorMapper fornecedorMapper;
    @InjectMocks
    private CriarFornecedorInteractor interactor;

    @Test
    void deveLancarExcecaoAoCriarFornecedorComCnpjJaExistenteNaBase() {
        var expectedMessage = "Já existe Fornecedor com o CNPJ informado!";
        var ex = new FornecedorCnpjInvalidException();
        doThrow(ex).when(presenter).cnpjInvalido();

        var requestModel = CreateFornecedorRequestModelBuilder.umFornecedor().get();

        when(fornecedorDsGateway.existeFornecedorComCnpj(requestModel.getCnpj())).thenReturn(true);

        // execucao
        try {
            interactor.execute(requestModel);
            Assertions.fail("Deveria lançar exceção de fornecedor já existente com cnpj informado");
        } catch (Exception actualException) {
            assertEquals(BusinessRuleException.class, actualException.getClass());
            assertEquals(expectedMessage, actualException.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarFornecedorComCnpjInvalido() {
        // cenario
        var expectedMessage = "CNPJ inválido!";

        var expectedException = new FornecedorCnpjInvalidException();
        doThrow(expectedException).when(presenter).cnpjInvalido();

        var cnpjInvalido = "99999999999999";
        var requestModel = CreateFornecedorRequestModelBuilder.umFornecedor().comCnpj(cnpjInvalido).get();

        // execucao
        try {
            interactor.execute(requestModel);
            Assertions.fail("Deveria lançar exceção de CNPJ inválido");
        } catch (Exception actualException) {
            assertEquals(expectedException.getClass(), actualException.getClass());
            assertEquals(expectedException.getMessage(), actualException.getMessage());
        }
    }

    @Test
    void executarComSucesso() {
        // cenario
        Fornecedor fornecedor = umFornecedor().get();

        when(fornecedorMapper
                .toDomain(any(CreateFornecedorModel.class))).thenReturn(fornecedor);

        when(fornecedorDsGateway.criar(fornecedor)).thenReturn(1);

        var responseModel = umFornecedorResponseModel().get();

        when(presenter.present(1)).thenReturn(responseModel);

        var requestModel = CreateFornecedorRequestModelBuilder.umFornecedor().get();

        // execucao
        var actualResponseModel = interactor.execute(requestModel);

        // verificacao
        assertNotNull(actualResponseModel);
        assertNotNull(actualResponseModel.get());
        assertEquals(1, actualResponseModel.get());
    }

}