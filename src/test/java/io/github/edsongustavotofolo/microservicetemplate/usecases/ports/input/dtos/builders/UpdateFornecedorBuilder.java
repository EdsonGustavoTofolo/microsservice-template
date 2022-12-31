package io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateFornecedor;

import java.util.List;

public final class UpdateFornecedorBuilder {

    private UpdateFornecedor.UpdateFornecedorBuilder updateFornecedor;

    private UpdateFornecedorBuilder() {}

    public static UpdateFornecedor.UpdateFornecedorBuilder updateFornecedor() {
        final var builder = new UpdateFornecedorBuilder();

        builder.updateFornecedor = UpdateFornecedor.builder();
        builder.updateFornecedor.id(1);
        builder.updateFornecedor.razaoSocial("Fornecedor Ltda");
        builder.updateFornecedor.nomeFantasia("Fornecedor & Cia");
        builder.updateFornecedor.logradouro("Rua Uruguai");
        builder.updateFornecedor.numero("95191");
        builder.updateFornecedor.bairro("Centro");
        builder.updateFornecedor.complemento("edificio");
        builder.updateFornecedor.pontoDeReferencia("proximo a Farmacia do Povo");
        builder.updateFornecedor.cep("85601000");
        builder.updateFornecedor.cidade(4115200);
        builder.updateFornecedor.contatos(List.of(
                UpdateContato.email(1, "person@mail.com"),
                UpdateContato.telefone(2, "49","35202222"),
                UpdateContato.celular(3, "49", "991053588"),
                UpdateContato.site(4, "https://fornecedor.com"),
                UpdateContato.outro(5, "0800 8080 1234")));

        return builder.updateFornecedor;
    }
}
