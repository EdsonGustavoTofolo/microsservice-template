package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.update.UpdateFornecedorRequestBuilder;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.UpdateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateFornecedorImplUnitTest {

    @Mock
    private UpdateFornecedorInputPort inputPort;
    @InjectMocks
    private UpdateFornecedorImpl updateFornecedor;

    @Captor
    private ArgumentCaptor<UpdateFornecedor> updateFornecedorArgumentCaptor;

    @Test
    void executeSuccessfully() throws BusinessRuleException {
        final var request = UpdateFornecedorRequestBuilder.updateFornecedorRequest().build();

        this.updateFornecedor.execute(1, request);

        verify(this.inputPort).execute(this.updateFornecedorArgumentCaptor.capture());

        final var model = this.updateFornecedorArgumentCaptor.getValue();

        assertNotNull(model);
        assertEquals(1, model.getId());
        assertEquals(request.getRazaoSocial(), model.getRazaoSocial());
        assertEquals(request.getNomeFantasia(), model.getNomeFantasia());
        assertEquals(request.getObservacao(), model.getObservacao());

        assertEquals(request.getEndereco().getLogradouro(), model.getLogradouro());
        assertEquals(request.getEndereco().getNumero(), model.getNumero());
        assertEquals(request.getEndereco().getBairro(), model.getBairro());
        assertEquals(request.getEndereco().getComplemento(), model.getComplemento());
        assertEquals(request.getEndereco().getPontoDeReferencia(), model.getPontoDeReferencia());
        assertEquals(request.getEndereco().getCep(), model.getCep());
        assertEquals(request.getEndereco().getCidade(), model.getCidade());

        assertEquals(request.getObservacaoContatos(), model.getObservacaoContatos());
        assertNotNull(model.getContatos());
        assertEquals(request.getContatos().size(), model.getContatos().size());

        for (int i = 0; i < model.getContatos().size(); i++) {
            final var actualContato = model.getContatos().get(i);
            final var expectedContato = request.getContatos().get(i);

            assertNotNull(actualContato);
            assertEquals(expectedContato.getId(), actualContato.getId());
            assertEquals(expectedContato.getTipoDeContato(), actualContato.getTipoDeContato().name());
            assertEquals(expectedContato.getDdd(), actualContato.getDdd());
            assertEquals(expectedContato.getNumero(), actualContato.getNumero());
            assertEquals(expectedContato.getUrlSite(), actualContato.getUrlSite());
            assertEquals(expectedContato.getTexto(), actualContato.getTexto());
            assertEquals(expectedContato.getEnderecoEmail(), actualContato.getEnderecoEmail());
        }
    }
}