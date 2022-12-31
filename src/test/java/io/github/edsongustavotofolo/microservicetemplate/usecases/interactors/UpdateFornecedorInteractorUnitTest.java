package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.UpdateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    private static Stream<Arguments> provideUpdateFornecedor() {
        final var fornecedor = updateFornecedor()
                .razaoSocial("Minha Razao Social")
                .nomeFantasia("Meu Nome Fantasia")
                .logradouro("Rua dos Centros")
                .numero("8181")
                .bairro("Joaquim")
                .complemento("E")
                .pontoDeReferencia("Edificio XYZ")
                .cep("89805000")
                .cidade(1)
                .observacaoContatos("Somente whatsApp")
                .observacao("browFornecedor");

        return Stream.of(
                Arguments.of(
                        Named.of("Adicionando um celular e removendo todos",
                                fornecedor.contatos(List.of(UpdateContato.celular(null, "49", "991805599"))).build())
                ),
                Arguments.of(
                        Named.of("Adicionando um contato, atualizado um contato e removendo o resto",
                                fornecedor.contatos(
                                        List.of(
                                                UpdateContato.email(1, "newemail@gmail.com"),
                                                UpdateContato.celular(null, "49", "991805599")
                                        )).build())
                ),
                Arguments.of(
                        Named.of("Atualizando todos os contatos",
                                fornecedor.contatos(
                                        List.of(
                                                UpdateContato.email(1, "newemail@gmail.com"),
                                                UpdateContato.telefone(2, "40", "35244658"),
                                                UpdateContato.celular(3, "45", "985235445"),
                                                UpdateContato.site(4, "https://newfornecedor.com.br"),
                                                UpdateContato.outro(5, "0800 9000 5000")
                                        )).build())
                ),
                Arguments.of(
                        Named.of("Atualiza todos os contatos e adiciona dois contatos",
                                fornecedor.contatos(
                                        List.of(
                                                UpdateContato.email(1, "newemail@gmail.com"),
                                                UpdateContato.telefone(2, "40", "35244658"),
                                                UpdateContato.celular(3, "45", "985235445"),
                                                UpdateContato.site(4, "https://newfornecedor.com.br"),
                                                UpdateContato.outro(5, "0800 9000 5000"),
                                                UpdateContato.email(null, "newemail@gmail.com"),
                                                UpdateContato.celular(null, "49", "991805599")
                                        )).build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideUpdateFornecedor")
    void shouldExecuteSuccessfully(final UpdateFornecedor expectedFornecedor) throws BusinessRuleException {
        when(this.fornecedorProvider.getById(any())).thenReturn(Optional.of(umFornecedor().build()));

        this.interactor.execute(expectedFornecedor);

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
        assertEquals(expectedFornecedor.getContatos().size(), actualFornecedor.getContatos().getSize());

        for (int i = 0; i < expectedFornecedor.getContatos().size(); i++) {
            final var expectedContato = expectedFornecedor.getContatos().get(i);
            final var actualContato = actualFornecedor.getContatos().getAt(i);

            assertEquals(expectedContato.getTipoDeContato(), actualContato.getTipo());
            assertEquals(expectedContato.getId(), actualContato.getId());

            switch (actualContato.getTipo()) {
                case OUTRO -> assertEquals(expectedContato.getTexto(), actualFornecedor.getContatos().getOutroAt(i).getTexto());
                case EMAIL -> assertEquals(expectedContato.getEnderecoEmail(), actualFornecedor.getContatos().getEmailAt(i).getEndereco());
                case CELULAR -> {
                    assertEquals(expectedContato.getDdd(), actualFornecedor.getContatos().getCelularAt(i).getDdd());
                    assertEquals(expectedContato.getNumero(), actualFornecedor.getContatos().getCelularAt(i).getNumero());
                }
                case TELEFONE -> {
                    assertEquals(expectedContato.getDdd(), actualFornecedor.getContatos().getTelefoneAt(i).getDdd());
                    assertEquals(expectedContato.getNumero(), actualFornecedor.getContatos().getTelefoneAt(i).getNumero());
                }
                case SITE -> assertEquals(expectedContato.getUrlSite(), actualFornecedor.getContatos().getSiteAt(i).getUrl());
            }
        }
    }

}