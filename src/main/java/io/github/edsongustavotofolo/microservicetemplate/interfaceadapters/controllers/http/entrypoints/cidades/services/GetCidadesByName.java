package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades.services;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.cidades.dtos.GetCidadesByNameResponse;

import java.util.List;

public interface GetCidadesByName {
    List<GetCidadesByNameResponse> execute(final String name);
}
