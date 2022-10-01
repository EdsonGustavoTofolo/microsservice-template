package io.github.edsongustavotofolo.microservicetemplate.usecases.models.builders;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.UpdateContatoModel;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.UpdateFornecedorModel;

import java.util.ArrayList;
import java.util.List;

public class UpdateFornecedorRequestModelBuilder {
    private UpdateFornecedorModel requestModel;

    private UpdateFornecedorRequestModelBuilder() {}

    public static UpdateFornecedorRequestModelBuilder umFornecedor() {
        var builder = new UpdateFornecedorRequestModelBuilder();
        builder.requestModel = UpdateFornecedorModel.builder()
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
                .contatos(new ArrayList<>(List.of(
                        UpdateContatoModel.builder()
                                .id(1)
                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                .enderecoEmail("person@mymail.com")
                                .build(),
                        UpdateContatoModel.builder()
                                .id(2)
                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                                .ddd("49")
                                .numero("35202222")
                                .build(),
                        UpdateContatoModel.builder()
                                .id(3)
                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                                .ddd("49")
                                .numero("991053588")
                                .build(),
                        UpdateContatoModel.builder()
                                .id(4)
                                .tipoDeContato(TipoDeContatoEnum.SITE)
                                .urlSite("https://provider.dot")
                                .build(),
                        UpdateContatoModel.builder()
                                .id(5)
                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                                .texto("0800 9004 9004")
                                .build()
                )))
                .build();
        return builder;
    }

    public UpdateFornecedorRequestModelBuilder adicionarContato(UpdateContatoModel updateContatoModel) {
        this.requestModel.getContatos().add(updateContatoModel);
        return this;
    }

    public UpdateFornecedorModel get() {
        return this.requestModel;
    }
}
