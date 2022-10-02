package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.CreateFornecedorRequestModelBuilder;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedor;
import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.NovoFornecedorResponseModelBuilder.umFornecedorResponseModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateFornecedorInteractorUnitTest {
    @Mock
    private CreateFornecedorOutputPort presenter;
    @Mock
    private FornecedorProvider fornecedorProvider;
    @Mock
    private FornecedorMapper fornecedorMapper;
    @InjectMocks
    private CreateFornecedorInteractor interactor;

    @Test
    void deveLancarExcecaoAoCriarFornecedorComCnpjJaExistenteNaBase() throws BusinessRuleException {
        var expectedMessage = "Já existe Fornecedor com o CNPJ informado!";
        var ex = new FornecedorCnpjInvalidException();
        doThrow(ex).when(presenter).cnpjIsInvalid();

        var requestModel = CreateFornecedorRequestModelBuilder.umFornecedor().get();

        when(fornecedorProvider.existeFornecedorComCnpj(requestModel.getCnpj())).thenReturn(true);

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
    void deveLancarExcecaoAoCriarFornecedorComCnpjInvalido() throws BusinessRuleException {
        // cenario
        var expectedMessage = "CNPJ inválido!";

        var expectedException = new FornecedorCnpjInvalidException();
        doThrow(expectedException).when(presenter).cnpjIsInvalid();

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
    void executarComSucesso() throws BusinessRuleException {
        // cenario
        Fornecedor fornecedor = umFornecedor().get();

        when(fornecedorMapper
                .toDomain(any(CreateFornecedor.class))).thenReturn(fornecedor);

        when(fornecedorProvider.criar(fornecedor)).thenReturn(1);

        var responseModel = umFornecedorResponseModel().get();

//        when(presenter.present(1)).thenReturn(responseModel);

        var requestModel = CreateFornecedorRequestModelBuilder.umFornecedor().get();

        // execucao
//        var actualResponseModel = interactor.execute(requestModel);

        // verificacao
//        assertNotNull(actualResponseModel);
//        assertNotNull(actualResponseModel.get());
//        assertEquals(1, actualResponseModel.get());
    }

}