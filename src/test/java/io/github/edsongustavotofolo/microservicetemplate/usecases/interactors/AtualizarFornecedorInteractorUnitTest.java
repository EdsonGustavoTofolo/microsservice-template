package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Celular;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Email;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.OutroContato;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Site;
import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Telefone;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.impl.ContatoMapperImpl;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.fixtures.UpdateFornecedorRequestModelFixture;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.UpdateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarFornecedorInteractorUnitTest {
    @Mock
    private FornecedorProvider fornecedorProvider;
    @Mock
    private UpdateFornecedorOutputPort presenter;
    @Mock
    private ContatoMapperImpl contatoMapper;
    @InjectMocks
    private UpdateFornecedorInteractor interactor;

    @Test
    void deveAtualizarFornecedorComSucesso() throws BusinessRuleException {
        // plan
        final var id = 1;

        final var fornecedorResult = FornecedorBuilder.umFornecedor().get();
        when(this.fornecedorProvider.getById(id)).thenReturn(Optional.of(fornecedorResult));
        when(this.contatoMapper.toDomain(any(UpdateContato.class))).thenCallRealMethod();

        final UpdateFornecedorRequest expected = UpdateFornecedorRequestModelFixture.umFornecedor()
                .adicionarContato(UpdateContato.builder()
                        .id(6)
                        .tipoDeContato(TipoDeContatoEnum.EMAIL)
                        .enderecoEmail("novoemail@fornecedor.com")
                        .build())
                .get();

        // do
        this.interactor.execute(id, null);

        // check
        final ArgumentCaptor<Fornecedor> argumentCaptor = ArgumentCaptor.forClass(Fornecedor.class);
        verify(this.fornecedorProvider, times(1)).update(argumentCaptor.capture());
        final var actual = argumentCaptor.getValue();

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
        assertEquals(expected.getCidade(), actual.getEndereco().getCidade().getId());

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