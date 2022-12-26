package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.presenters;

import io.github.edsongustavotofolo.microservicetemplate.domain.entities.Cidade;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.cidades.dtos.GetCidadesByNameResponse;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.output.GetCidadesByNameOuputPort;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GetCidadesByNameHttpPresenter implements GetCidadesByNameOuputPort<GetCidadesByNameResponse> {

    private List<GetCidadesByNameResponse> response;

    @Override
    public void show(final List<Cidade> cidades) {
        this.response = cidades.stream().map(cidade ->
                GetCidadesByNameResponse.builder()
                        .id(cidade.getId())
                        .nome(cidade.getNome())
                        .estadoId(cidade.getEstado().getId())
                        .estadoNome(cidade.getEstado().getNome())
                        .paisId(cidade.getEstado().getPais().getId())
                        .paisNome(cidade.getEstado().getPais().getNome())
                        .build()
        ).toList();
    }

    @Override
    public List<GetCidadesByNameResponse> get() {
        return this.response;
    }
}
