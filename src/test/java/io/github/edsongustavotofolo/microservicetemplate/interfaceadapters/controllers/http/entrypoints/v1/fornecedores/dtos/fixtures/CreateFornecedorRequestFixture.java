package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.fixtures;

import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateContatoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.CreateFornecedorRequest;

import java.util.List;

public class CreateFornecedorRequestFixture {

    private CreateFornecedorRequestFixture() {}

    public static CreateFornecedorRequest createFornecedorRequest() {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComCnpj(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj(value)
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComRazaoSocial(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial(value)
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComNomeFantasia(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia(value)
                .observacao("Observacao")
                .logradouro("Rua Washington Luis")
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComLogradouro(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro(value)
                .numero("210")
                .bairro("São Cristóvão")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComNumero(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero(value)
                .bairro("Bairro")
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComBairro(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro(value)
                .complemento("E")
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComComplemento(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro("bairro")
                .complemento(value)
                .pontoDeReferencia("Edificio XPTO, apto 1000")
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComPontoDeReferencia(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro("bairro")
                .complemento("Complemento")
                .pontoDeReferencia(value)
                .cep("89803000")
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComCep(final String value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro("bairro")
                .complemento("Complemento")
                .pontoDeReferencia("Ponto de Referencia")
                .cep(value)
                .cidade(151515)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComCidade(final Integer value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro("bairro")
                .complemento("Complemento")
                .pontoDeReferencia("Ponto de Referencia")
                .cep("85605000")
                .cidade(value)
                .contatos(List.of(
                        CreateContatoRequest.outro("0800 123 123 123"),
                        CreateContatoRequest.site("https://dns.com.br/mysite")))
                .observacaoContatos("meus contatos")
                .build();
    }

    public static CreateFornecedorRequest createFornecedorRequestComContatos(final List<CreateContatoRequest> value) {
        return CreateFornecedorRequest.builder()
                .cnpj("68278677000120")
                .razaoSocial("Razao Social")
                .nomeFantasia("Nome Fantasia")
                .observacao("Observacao")
                .logradouro("Logradouro")
                .numero("210")
                .bairro("bairro")
                .complemento("Complemento")
                .pontoDeReferencia("Ponto de Referencia")
                .cep("85605000")
                .cidade(123456)
                .contatos(value)
                .observacaoContatos("meus contatos")
                .build();
    }
}