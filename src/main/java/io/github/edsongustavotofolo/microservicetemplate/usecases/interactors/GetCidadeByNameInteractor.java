package io.github.edsongustavotofolo.microservicetemplate.usecases.interactors;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.GetCidadesByNameInputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.GetCidadesByNameOuputPort;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCidadeByNameInteractor implements GetCidadesByNameInputPort {

    private final GetCidadesByNameOuputPort presenter;
    private final CidadeProvider cidadeProvider;

    @Override
    public void execute(final String name) {
        final var cidades = this.cidadeProvider.getByName(name);
        this.presenter.show(cidades);
    }
}
