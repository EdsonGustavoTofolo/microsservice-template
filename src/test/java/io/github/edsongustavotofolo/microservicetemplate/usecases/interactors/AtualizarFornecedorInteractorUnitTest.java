package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.*;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.UpdateFornecedorRequestModelBuilder;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl.ContatoMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.FornecedorAtualizadoOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarFornecedorInteractorUnitTest {
    @Mock
    private FornecedorProvider fornecedorProvider;
    @Mock
    private FornecedorAtualizadoOutputPort presenter;
    @Mock
    private ContatoMapperImpl contatoMapper;
    @InjectMocks
    private AtualizarFornecedorInteractor interactor;

    @Test
    void deveAtualizarFornecedorComSucesso() throws BusinessRuleException {
        // plan
        var id = 1;

        var fornecedorResult = FornecedorBuilder.umFornecedor().get();
        when(fornecedorProvider.buscarPorId(id)).thenReturn(Optional.of(fornecedorResult));
        when(contatoMapper.toDomain(any(UpdateContato.class))).thenCallRealMethod();

        UpdateFornecedor expected = UpdateFornecedorRequestModelBuilder.umFornecedor()
                .adicionarContato(UpdateContato.builder()
                        .id(6)
                        .tipoDeContato(TipoDeContatoEnum.EMAIL)
                        .enderecoEmail("novoemail@fornecedor.com")
                        .build())
                .get();

        // do
        interactor.execute(id, expected);

        // check
        ArgumentCaptor<Fornecedor> argumentCaptor = ArgumentCaptor.forClass(Fornecedor.class);
        verify(fornecedorProvider, times(1)).atualizar(argumentCaptor.capture());
        var actual = argumentCaptor.getValue();

        assertEquals(expected.getCnpj(), actual.getCnpj().toString());
        assertEquals(expected.getNomeFantasia(), actual.getNomeFantasia());
        assertEquals(expected.getRazaoSocial(), actual.getRazaoSocial());
        assertEquals(expected.getObservacao(), actual.getObservacao());

        assertEquals(expected.getLogradouro(), actual.getEndereco().getLogradouro());
        assertEquals(expected.getNumero(), actual.getEndereco().getNumero());
        assertEquals(expected.getBairro(), actual.getEndereco().getBairro());
        assertEquals(expected.getComplemento(), actual.getEndereco().getComplemento());
        assertEquals(expected.getPontoDeReferencia(), actual.getEndereco().getPontoDeReferencia());
        assertEquals(expected.getCep(), actual.getEndereco().getCep().toString());
        assertEquals(expected.getCidadeId(), actual.getEndereco().getCidade().getId());

        assertEquals(6, actual.getContatos().getLista().size());

        assertEquals(Email.class, actual.getContatos().getLista().get(0).getClass());
        assertEquals(expected.getContatos().get(0).getEnderecoEmail(), actual.getContatos().getEmailAt(0).toString());

        assertEquals(Telefone.class, actual.getContatos().getLista().get(1).getClass());
        assertEquals(expected.getContatos().get(1).getDdd(), actual.getContatos().getTelefoneAt(1).getDdd());
        assertEquals(expected.getContatos().get(1).getNumero(), actual.getContatos().getTelefoneAt(1).getNumero());

        assertEquals(Celular.class, actual.getContatos().getLista().get(2).getClass());
        assertEquals(expected.getContatos().get(2).getDdd(), actual.getContatos().getCelularAt(2).getDdd());
        assertEquals(expected.getContatos().get(2).getNumero(), actual.getContatos().getCelularAt(2).getNumero());

        assertEquals(Site.class, actual.getContatos().getLista().get(3).getClass());
        assertEquals(expected.getContatos().get(3).getUrlSite(), actual.getContatos().getSiteAt(3).toString());

        assertEquals(OutroContato.class, actual.getContatos().getLista().get(4).getClass());
        assertEquals(expected.getContatos().get(4).getTexto(), actual.getContatos().getOutroAt(4).toString());

        assertEquals(Email.class, actual.getContatos().getLista().get(5).getClass());
        assertEquals(expected.getContatos().get(5).getEnderecoEmail(), actual.getContatos().getEmailAt(5).toString());
    }
}