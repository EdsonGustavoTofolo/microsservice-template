package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.CreateFornecedorModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;

import java.util.List;

public class CreateFornecedorRequestModelBuilder {
    private CreateFornecedorModel requestModel;

    private CreateFornecedorRequestModelBuilder() {}

    public static CreateFornecedorRequestModelBuilder umFornecedor() {
        var builder = new CreateFornecedorRequestModelBuilder();
        builder.requestModel = CreateFornecedorModel.builder()
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
                .contatos(List.of(
                        CreateContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                .enderecoEmail("person@mymail.com")
                                .build(),
                        CreateContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                                .ddd("49")
                                .numero("35202222")
                                .build(),
                        CreateContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                                .ddd("49")
                                .numero("991053588")
                                .build(),
                        CreateContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.SITE)
                                .urlSite("https://fornecedor.com")
                                .build(),
                        CreateContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                                .texto("0800 8080 1234")
                                .build()
                ))
                .build();
        return builder;
    }

    public CreateFornecedorRequestModelBuilder comObservacao(final String observacao) {
        this.requestModel.setObservacao(observacao);
        return this;
    }

    public CreateFornecedorRequestModelBuilder comNomeFantasia(final String nomeFantasia) {
        this.requestModel.setNomeFantasia(nomeFantasia);
        return this;
    }

    public CreateFornecedorRequestModelBuilder comRazaoSocial(final String razaoSocial) {
        this.requestModel.setRazaoSocial(razaoSocial);
        return this;
    }

    public CreateFornecedorRequestModelBuilder comCnpj(final String cnpj) {
        this.requestModel.setCnpj(cnpj);
        return this;
    }

    public CreateFornecedorModel get() {
        return this.requestModel;
    }
}
