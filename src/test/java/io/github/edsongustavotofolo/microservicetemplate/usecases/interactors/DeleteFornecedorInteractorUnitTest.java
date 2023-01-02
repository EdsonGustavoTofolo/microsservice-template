package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.DeleteFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteFornecedorInteractorUnitTest {

    @Mock
    private FornecedorProvider provider;
    @Mock
    private DeleteFornecedorOutputPort presenter;
    @InjectMocks
    private DeleteFornecedorInteractor interactor;

    @Captor
    private ArgumentCaptor<Integer> idArgumentCaptor;

    @DisplayName("Delete Fornecedor - Sucesso")
    @Test
    void shouldDeleteFornecedorSuccessfully() throws BusinessRuleException {
        when(this.provider.existsFornecedorById(any())).thenReturn(true);

        this.interactor.execute(1);

        verify(this.provider).delete(this.idArgumentCaptor.capture());

        final var id = this.idArgumentCaptor.getValue();

        assertThat(id).isEqualTo(1);
    }

    @DisplayName("Delete Fornecedor - Does not")
    @Test
    void shouldAvoidDeleteFornecedor() throws BusinessRuleException {
        when(this.provider.existsFornecedorById(any())).thenReturn(false);

        this.interactor.execute(1);

        verifyNoMoreInteractions(this.provider);

        verify(this.presenter).fornecedorNaoEncontrado();
    }
}