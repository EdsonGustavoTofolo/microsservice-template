package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.UpdateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedor;
import static io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.builders.UpdateFornecedorBuilder.updateFornecedor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateFornecedorInteractorUnitTest {

    @Mock
    private FornecedorProvider fornecedorProvider;
    @Mock
    private UpdateFornecedorOutputPort presenter;
    @InjectMocks
    private UpdateFornecedorInteractor interactor;

    @Captor
    private ArgumentCaptor<Fornecedor> fornecedorArgumentCaptor;

    @Test
    void shouldExecuteSuccessfully() throws BusinessRuleException {
        final var savedFornecedor = umFornecedor().build();

        when(this.fornecedorProvider.getById(any())).thenReturn(Optional.of(savedFornecedor));

        final var expectedFornecedor = updateFornecedor()
                .razaoSocial("Minha Razao Social")
                .nomeFantasia("Meu Nome Fantasia")
                .logradouro("Rua dos Centros")
                .numero("8181")
                .bairro("Joaquim")
                .complemento("E")
                .pontoDeReferencia("Edificio XYZ")
                .cep("89805000")
                .cidade(1)
                .contatos(List.of(UpdateContato.celular("49", "991805599")))
                .observacaoContatos("Somente whatsApp")
                .observacao("browFornecedor")
                .build();

        this.interactor.execute(expectedFornecedor);

//        verifyNoInteractions(this.presenter);

        verify(this.fornecedorProvider).update(this.fornecedorArgumentCaptor.capture());

        final var actualFornecedor = this.fornecedorArgumentCaptor.getValue();

        assertNotNull(actualFornecedor);
        assertEquals(expectedFornecedor.getRazaoSocial(), actualFornecedor.getRazaoSocial());
        assertEquals(expectedFornecedor.getNomeFantasia(), actualFornecedor.getNomeFantasia());
        assertEquals(expectedFornecedor.getLogradouro(), actualFornecedor.getEndereco().getLogradouro());
        assertEquals(expectedFornecedor.getLogradouro(), actualFornecedor.getEndereco().getLogradouro());
        assertEquals(expectedFornecedor.getNumero(), actualFornecedor.getEndereco().getNumero());
        assertEquals(expectedFornecedor.getBairro(), actualFornecedor.getEndereco().getBairro());
        assertEquals(expectedFornecedor.getCep(), actualFornecedor.getEndereco().getCep().toString());
        assertEquals(expectedFornecedor.getCidade(), actualFornecedor.getEndereco().getCidade().getId());
        assertEquals(expectedFornecedor.getObservacao(), actualFornecedor.getObservacao());

        assertEquals(expectedFornecedor.getObservacaoContatos(), actualFornecedor.getContatos().getObservacao());
        // assertEquals(expectedFornecedor.getContatos().size(), actualFornecedor.getContatos().getSize());

        assertEquals(expectedFornecedor.getContatos().get(0).getDdd(), actualFornecedor.getContatos().getCelularAt(0).getDdd());
        assertEquals(expectedFornecedor.getContatos().get(0).getNumero(), actualFornecedor.getContatos().getCelularAt(0).getNumero());
    }

}