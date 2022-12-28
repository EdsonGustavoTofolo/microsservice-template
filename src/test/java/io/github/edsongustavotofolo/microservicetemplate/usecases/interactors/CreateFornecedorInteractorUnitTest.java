package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters.exceptions.FornecedorCnpjInvalidException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.builders.CreateFornecedorBuilder.createFornecedor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateFornecedorInteractorUnitTest {

    @Mock
    private CreateFornecedorOutputPort presenter;
    @Mock
    private FornecedorProvider fornecedorProvider;
    @InjectMocks
    private CreateFornecedorInteractor interactor;

    @Captor
    private ArgumentCaptor<Fornecedor> fornecedorArgumentCaptor;
    @Captor
    private ArgumentCaptor<Integer> idArgumentCaptor;

    @Test
    void deveLancarExcecaoAoCriarFornecedorComCnpjJaExistenteNaBase() throws BusinessRuleException {
        final var expectedMessage = "Já existe Fornecedor com o CNPJ informado!";

        final var ex = new FornecedorCnpjInvalidException();

        doThrow(ex).when(this.presenter).fornecedorAlreadyExists();

        when(this.fornecedorProvider.existsFornecedorWithCnpj(anyString())).thenReturn(true);

        // execucao
        try {
            this.interactor.execute(createFornecedor().build());
            Assertions.fail("Deveria lançar exceção de fornecedor já existente com cnpj informado");
        } catch (final Exception actualException) {
            assertEquals(FornecedorCnpjInvalidException.class, actualException.getClass());
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
        final var requestModel = createFornecedor().cnpj(cnpjInvalido).build();

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
    void shouldCreateFornecedorSuccessfully() throws BusinessRuleException {
        // cenario
        when(this.fornecedorProvider.existsFornecedorWithCnpj(any())).thenReturn(false);
        when(this.fornecedorProvider.create(any())).thenReturn(1);

        final var requestModel = createFornecedor().build();

        // execucao
        this.interactor.execute(requestModel);

        // verificação
        verify(this.fornecedorProvider).create(this.fornecedorArgumentCaptor.capture());

        final var fornecedor = this.fornecedorArgumentCaptor.getValue();

        assertNotNull(fornecedor);

        verify(this.presenter).show(this.idArgumentCaptor.capture());

        final var id = this.idArgumentCaptor.getValue();

        assertNotNull(id);
        assertEquals(1, id);
    }

}