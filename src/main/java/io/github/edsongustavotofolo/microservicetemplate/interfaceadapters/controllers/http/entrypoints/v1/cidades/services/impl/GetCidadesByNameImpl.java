package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.cidades.services.impl;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.cidades.dtos.GetCidadesByNameResponse;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.cidades.services.GetCidadesByName;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.GetCidadesByNameInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.GetCidadesByNameOuputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCidadesByNameImpl implements GetCidadesByName {

    private final GetCidadesByNameInputPort getCidadesByName;
    private final GetCidadesByNameOuputPort<GetCidadesByNameResponse> presenter;

    @Override
    public List<GetCidadesByNameResponse> execute(final String name) {
        this.getCidadesByName.execute(name);
        return this.presenter.get();
    }
}
