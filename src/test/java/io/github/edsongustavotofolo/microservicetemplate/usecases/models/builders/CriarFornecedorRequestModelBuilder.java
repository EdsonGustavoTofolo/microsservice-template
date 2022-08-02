package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.models.ContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.CriarFornecedorRequestModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.models.TipoDeContatoEnum;

import java.util.List;

public class CriarFornecedorRequestModelBuilder {
    private CriarFornecedorRequestModel requestModel;

    private CriarFornecedorRequestModelBuilder() {}

    public static CriarFornecedorRequestModelBuilder umFornecedorRequestModel() {
        var builder = new CriarFornecedorRequestModelBuilder();
        builder.requestModel = CriarFornecedorRequestModel.builder()
                .cnpj("45135006000104")
                .razaoSocial("Fornecedor Ltda")
                .nomeFantasia("Fornecedor & Cia")
                .logradouro("Rua Sao Miguel do Oeste")
                .numero("1111")
                .bairro("Jardim das Americas")
                .complemento("apto 701, bloco A")
                .pontoDeReferencia("prox a Farmacia")
                .cep("85601000")
                .cidadeId(4115200)
                .contatos(List.of(
                        ContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                .enderecoEmail("person@mymail.com")
                                .build(),
                        ContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                                .ddd("49")
                                .numero("35202222")
                                .build(),
                        ContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                                .ddd("49")
                                .numero("991053588")
                                .build(),
                        ContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.SITE)
                                .urlSite("https://fornecedor.com")
                                .build(),
                        ContatoModel.builder()
                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                                .texto("0800 8080 1234")
                                .build()
                ))
                .build();
        return builder;
    }

    public CriarFornecedorRequestModel get() {
        return this.requestModel;
    }
}
