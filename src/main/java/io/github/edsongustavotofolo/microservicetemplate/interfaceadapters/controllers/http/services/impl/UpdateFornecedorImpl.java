package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.mappers.UpdateFornecedorMapper;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.services.UpdateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.UpdateFornecedorInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.exceptions.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateFornecedorImpl implements UpdateFornecedor {

    private final UpdateFornecedorInputPort updateFornecedor;

    @Override
    public void execute(final Integer id, final UpdateFornecedorRequest updateFornecedorRequest) throws BusinessRuleException {
        final var model = UpdateFornecedorMapper.INSTANCE.toModel(updateFornecedorRequest);
        this.updateFornecedor.execute(id, model);
    }
}
