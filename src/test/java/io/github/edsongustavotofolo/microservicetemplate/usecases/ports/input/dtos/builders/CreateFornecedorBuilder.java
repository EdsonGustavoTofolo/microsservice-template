package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;

import java.util.List;

public final class CreateFornecedorBuilder {

    private CreateFornecedor.CreateFornecedorBuilder createFornecedor;

    private CreateFornecedorBuilder() {}

    public static CreateFornecedor.CreateFornecedorBuilder createFornecedor() {
        final var builder = new CreateFornecedorBuilder();

        builder.createFornecedor = CreateFornecedor.builder();

        builder.createFornecedor.cnpj("45135006000104");
        builder.createFornecedor.razaoSocial("Fornecedor Ltda");
        builder.createFornecedor.nomeFantasia("Fornecedor & Cia");
        builder.createFornecedor.logradouro("Rua Uruguai");
        builder.createFornecedor.numero("95191");
        builder.createFornecedor.bairro("Centro");
        builder.createFornecedor.complemento("edificio");
        builder.createFornecedor.pontoDeReferencia("proximo a Farmacia do Povo");
        builder.createFornecedor.cep("85601000");
        builder.createFornecedor.cidade(4115200);
        builder.createFornecedor.contatos(List.of(
                CreateContato.email("person@mail.com"),
                CreateContato.telefone("49","35202222"),
                CreateContato.celular("49", "991053588"),
                CreateContato.site("https://fornecedor.com"),
                CreateContato.outro("0800 8080 1234")));

        return builder.createFornecedor;
    }
}
