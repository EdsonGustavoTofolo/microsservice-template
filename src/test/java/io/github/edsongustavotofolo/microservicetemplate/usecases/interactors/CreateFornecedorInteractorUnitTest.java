package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.fixtures.CreateFornecedorRequestModelFixture;
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
import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.fixtures.NovoFornecedorResponseModelFixture.umFornecedorResponseModel;
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
        final var expectedMessage = "Já existe Fornecedor com o CNPJ informado!";
        final var ex = new FornecedorCnpjInvalidException();
        doThrow(ex).when(this.presenter).cnpjIsInvalid();

        final var requestModel = CreateFornecedorRequestModelFixture.createFornecedor();

        when(this.fornecedorProvider.existsFornecedorWithCnpj(requestModel.getCnpj())).thenReturn(true);

        // execucao
        try {
            this.interactor.execute(requestModel);
            Assertions.fail("Deveria lançar exceção de fornecedor já existente com cnpj informado");
        } catch (final Exception actualException) {
            assertEquals(BusinessRuleException.class, actualException.getClass());
            assertEquals(expectedMessage, actualException.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoAoCriarFornecedorComCnpjInvalido() throws BusinessRuleException {
        // cenario
        final var expectedMessage = "CNPJ inválido!";

        final var expectedException = new FornecedorCnpjInvalidException();
        doThrow(expectedException).when(this.presenter).cnpjIsInvalid();

        final var cnpjInvalido = "99999999999999";
        final var requestModel = CreateFornecedorRequestModelFixture.createFornecedorComCnpj(cnpjInvalido);

        // execucao
        try {
            this.interactor.execute(requestModel);
            Assertions.fail("Deveria lançar exceção de CNPJ inválido");
        } catch (final Exception actualException) {
            assertEquals(expectedException.getClass(), actualException.getClass());
            assertEquals(expectedException.getMessage(), actualException.getMessage());
        }
    }

    @Test
    void executarComSucesso() throws BusinessRuleException {
        // cenario
        final Fornecedor fornecedor = umFornecedor().get();

        when(this.fornecedorMapper
                .toDomain(any(CreateFornecedor.class))).thenReturn(fornecedor);

        when(this.fornecedorProvider.create(fornecedor)).thenReturn(1);

        final var responseModel = umFornecedorResponseModel().get();

//        when(presenter.present(1)).thenReturn(responseModel);

        final var requestModel = CreateFornecedorRequestModelFixture.createFornecedor();

        // execucao
//        var actualResponseModel = interactor.execute(requestModel);

        // verificacao
//        assertNotNull(actualResponseModel);
//        assertNotNull(actualResponseModel.get());
//        assertEquals(1, actualResponseModel.get());
    }

}