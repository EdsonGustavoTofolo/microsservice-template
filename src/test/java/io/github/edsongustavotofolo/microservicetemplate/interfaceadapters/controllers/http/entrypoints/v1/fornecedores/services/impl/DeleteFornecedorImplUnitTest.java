package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.DeleteFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteFornecedorImplUnitTest {

    @Mock
    private DeleteFornecedorInputPort inputPort;
    @InjectMocks
    private DeleteFornecedorImpl deleteFornecedor;

    @Captor
    private ArgumentCaptor<Integer> idArgumentCaptor;

    @DisplayName("Delete Fornecedor - Successfully")
    @Test
    void shouldDeleteFornecedorSuccessfully() throws BusinessRuleException {
        this.deleteFornecedor.execute(1);

        verify(this.inputPort).execute(this.idArgumentCaptor.capture());

        final var id = this.idArgumentCaptor.getValue();

        assertThat(id).isEqualTo(1);
    }
}