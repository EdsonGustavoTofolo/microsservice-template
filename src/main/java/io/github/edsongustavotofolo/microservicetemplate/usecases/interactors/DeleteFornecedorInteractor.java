package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.DeleteFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.DeleteFornecedorOutputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.FornecedorProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteFornecedorInteractor implements DeleteFornecedorInputPort {

    private final FornecedorProvider fornecedorProvider;
    private final DeleteFornecedorOutputPort presenter;

    @Transactional
    @Override
    public void execute(final Integer id) throws BusinessRuleException {
        log.info("Deleting fornecedor.");

        log.info("Checking if fornecedor exists.");

        if (!this.fornecedorProvider.existsFornecedorById(id)) {
            log.error("Fornecedor not found.");
            this.presenter.fornecedorNaoEncontrado();
            return;
        }

        log.info("Requesting fornecedor deleting at provider.");

        this.fornecedorProvider.delete(id);

        log.info("Fornecedor deleted successfully.");
    }
}
