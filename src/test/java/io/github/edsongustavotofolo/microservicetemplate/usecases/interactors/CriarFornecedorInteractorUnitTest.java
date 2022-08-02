package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Fornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.gateways.FornecedorDsGateway;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.FornecedorCriadoOutputBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.edsongustavotofolo.microservicetemplate.domain.builder.FornecedorBuilder.umFornecedor;
import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.CriarFornecedorRequestModelBuilder.umFornecedorRequestModel;
import static io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders.NovoFornecedorResponseModelBuilder.umFornecedorResponseModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarFornecedorInteractorUnitTest {
    @Mock
    private FornecedorCriadoOutputBoundary presenter;
    @Mock
    private FornecedorDsGateway fornecedorDsGateway;
    @Mock
    private FornecedorMapper fornecedorMapper;
    @InjectMocks
    private CriarFornecedorInteractor interactor;

    @Test
    public void executarComSucesso() {
        // cenario
        Fornecedor fornecedor = umFornecedor().get();

        when(fornecedorMapper
                .toDomain(any(CriarFornecedorRequestModel.class))).thenReturn(fornecedor);

        when(fornecedorDsGateway.criar(fornecedor)).thenReturn(1);

        var responseModel = umFornecedorResponseModel().get();

        when(presenter.present(1)).thenReturn(responseModel);

        var requestModel = umFornecedorRequestModel().get();

        // execucao
        var actualResponseModel = interactor.execute(requestModel);

        // verificacao
        assertNotNull(actualResponseModel);
        assertNotNull(actualResponseModel.get());
        assertEquals(1, actualResponseModel.get());
    }

}