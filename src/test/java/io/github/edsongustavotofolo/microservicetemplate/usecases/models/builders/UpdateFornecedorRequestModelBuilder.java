package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.UpdateFornecedorRequest;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.UpdateContato;

public class UpdateFornecedorRequestModelBuilder {
    private UpdateFornecedorRequest requestModel;

    private UpdateFornecedorRequestModelBuilder() {
    }

    public static UpdateFornecedorRequestModelBuilder umFornecedor() {
        final var builder = new UpdateFornecedorRequestModelBuilder();
        builder.requestModel = UpdateFornecedorRequest.builder()
                .cnpj("45135006000104")
                .razaoSocial("Fornecedor Ltda")
                .nomeFantasia("Fornecedor & Cia")
                .logradouro("Rua Uruguai")
                .numero("95191")
                .bairro("Centro")
                .complemento("edificio")
                .pontoDeReferencia("proximo a Farmacia do Povo")
                .cep("85601000")
                .cidadeId(4115200)
                .contatos(null
//                        new ArrayList<>(List.of(
//                        UpdateContato.builder()
//                                .id(1)
//                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
//                                .enderecoEmail("person@mymail.com")
//                                .build(),
//                        UpdateContato.builder()
//                                .id(2)
//                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
//                                .ddd("49")
//                                .numero("35202222")
//                                .build(),
//                        UpdateContato.builder()
//                                .id(3)
//                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
//                                .ddd("49")
//                                .numero("991053588")
//                                .build(),
//                        UpdateContato.builder()
//                                .id(4)
//                                .tipoDeContato(TipoDeContatoEnum.SITE)
//                                .urlSite("https://provider.dot")
//                                .build(),
//                        UpdateContato.builder()
//                                .id(5)
//                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
//                                .texto("0800 9004 9004")
//                                .build()
//                ))
                )
                .build();
        return builder;
    }

    public UpdateFornecedorRequestModelBuilder adicionarContato(final UpdateContato updateContato) {
        this.requestModel.getContatos().add(null);
        return this;
    }

    public UpdateFornecedorRequest get() {
        return this.requestModel;
    }
}
