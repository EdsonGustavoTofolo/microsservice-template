package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores;

public final class FornecedorControllerPaths {

    public static final String BASE_PATH = "/api/microservice-template/v1/fornecedores";
    public static final String UPDATE_FORNECEDOR_PATH = "/{id}";

    private FornecedorControllerPaths() {
        throw new IllegalStateException("Utility class.");
    }
}
