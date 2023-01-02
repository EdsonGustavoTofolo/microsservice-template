package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects.Cnpj;
import io.github.edsongustavotofolo.microservicetemplate.usecases.interactors.mappers.FornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.CreateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.CreateFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFornecedorInteractor implements CreateFornecedorInputPort {

    private final CreateFornecedorOutputPort<?> presenter;
    private final FornecedorProvider fornecedorProvider;

    @Transactional
    @Override
    public void execute(final CreateFornecedor createFornecedor) throws BusinessRuleException {
        log.info("Running fornecedor creation.");

        log.info("Validating CNPJ.");
        if (Cnpj.numeroInvalido(createFornecedor.getCnpj())) {
            log.error("Fornecedor creation failed. CNPJ is invalid.");
            this.presenter.cnpjIsInvalid();
            return;
        }

        log.info("Checking if existing fornecedor by CNPJ.");
        if (this.fornecedorProvider.existsFornecedorWithCnpj(createFornecedor.getCnpj())) {
            log.error("Fornecedor creation failed. Fornecedor already exists with CNPJ entered.");
            this.presenter.fornecedorAlreadyExists();
            return;
        }

        final var fornecedor = FornecedorMapper.INSTANCE.map(createFornecedor);

        log.info("Requesting fornecedor creating at provider.");

        final var id = this.fornecedorProvider.create(fornecedor);

        this.presenter.show(id);

        log.info("Created fornecedor successfully.");
    }
}
