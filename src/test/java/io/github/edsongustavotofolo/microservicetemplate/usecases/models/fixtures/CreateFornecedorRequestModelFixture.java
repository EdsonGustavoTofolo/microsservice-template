package io.github.edsongustavotofolo.microservicetemplate.usecases.models.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateContato;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.input.dtos.CreateFornecedor;
import io.github.edsongustavotofolo.microservicetemplate.usecases.ports.dtos.TipoDeContatoEnum;

import java.util.List;

public class CreateFornecedorRequestModelFixture {

    private CreateFornecedorRequestModelFixture() {}

    public static CreateFornecedor createFornecedor() {
        return CreateFornecedor.builder()
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
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                .enderecoEmail("person@mymail.com")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                                .ddd("49")
                                .numero("35202222")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                                .ddd("49")
                                .numero("991053588")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.SITE)
                                .urlSite("https://fornecedor.com")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                                .texto("0800 8080 1234")
                                .build()
                ))
                .build();
    }

    public static CreateFornecedor createFornecedorComCnpj(final String cnpj) {
        return CreateFornecedor.builder()
                .cnpj(cnpj)
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
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.EMAIL)
                                .enderecoEmail("person@mymail.com")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.TELEFONE)
                                .ddd("49")
                                .numero("35202222")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.CELULAR)
                                .ddd("49")
                                .numero("991053588")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.SITE)
                                .urlSite("https://fornecedor.com")
                                .build(),
                        CreateContato.builder()
                                .tipoDeContato(TipoDeContatoEnum.OUTRO)
                                .texto("0800 8080 1234")
                                .build()
                ))
                .build();
    }
}
