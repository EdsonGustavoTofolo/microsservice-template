package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.builders.CreateFornecedorRequestBuilder;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.dtos.CreatedFornecedor;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateFornecedorImplUnitTest {

    @Mock
    private CreateFornecedorInputPort inputPort;
    @Mock
    private CreateFornecedorOutputPort<CreatedFornecedor> outputPort;
    @InjectMocks
    private CreateFornecedorImpl createFornecedor;

    @Captor
    private ArgumentCaptor<CreateFornecedor> inputPortArgumentCaptor;

    @Test
    void shouldCreateFornecedorSuccessfully() throws BusinessRuleException {
        when(this.outputPort.get()).thenReturn(new CreatedFornecedor(1));

        final var request = CreateFornecedorRequestBuilder.createFornecedorRequest().build();

        final var id = this.createFornecedor.execute(request);

        assertNotNull(id);
        assertEquals(1, id);

        verify(this.inputPort).execute(this.inputPortArgumentCaptor.capture());

        final var model = this.inputPortArgumentCaptor.getValue();

        assertNotNull(model);
        assertEquals(request.getCnpj(), model.getCnpj());
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

        assertNotNull(model.getContatos());
        assertEquals(2, model.getContatos().size());
        this.assertContatos(0, request, model);
        this.assertContatos(1, request, model);

        assertEquals(request.getObservacaoContatos(), model.getObservacaoContatos());
    }

    private void assertContatos(final int index, final CreateFornecedorRequest request, final CreateFornecedor model) {
        assertEquals(request.getContatos().get(index).getTipoDeContato(), model.getContatos().get(index).getTipoDeContato().name());
        assertEquals(request.getContatos().get(index).getDdd(), model.getContatos().get(index).getDdd());
        assertEquals(request.getContatos().get(index).getNumero(), model.getContatos().get(index).getNumero());
        assertEquals(request.getContatos().get(index).getEnderecoEmail(), model.getContatos().get(index).getEnderecoEmail());
        assertEquals(request.getContatos().get(index).getUrlSite(), model.getContatos().get(index).getUrlSite());
        assertEquals(request.getContatos().get(index).getTexto(), model.getContatos().get(index).getTexto());
    }
}