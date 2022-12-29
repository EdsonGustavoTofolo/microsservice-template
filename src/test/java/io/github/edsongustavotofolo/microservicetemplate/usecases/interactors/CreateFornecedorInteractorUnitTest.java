package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
    void naoDeveCriarFornecedorQuandoCnpjInformadoJaExiste() throws BusinessRuleException {
        when(this.fornecedorProvider.existsFornecedorWithCnpj(anyString())).thenReturn(true);

        this.interactor.execute(createFornecedor().build());

        verify(this.presenter).fornecedorAlreadyExists();

        verifyNoMoreInteractions(this.fornecedorProvider);
    }

    @Test
    void naoDeveCriarFornecedorQuandoCnpjInformadoForInvalido() throws BusinessRuleException {
        this.interactor.execute(createFornecedor().cnpj("99999999999999").build());

        verify(this.presenter).cnpjIsInvalid();

        verifyNoInteractions(this.fornecedorProvider);
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