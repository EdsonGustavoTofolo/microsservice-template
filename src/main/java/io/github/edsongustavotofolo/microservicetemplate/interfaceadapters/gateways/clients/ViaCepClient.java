package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "via-cep", url = "https://viacep.com.br/ws", configuration = ViaCepClientConfiguration.class)
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    Optional<ViaCepResponse> buscarPorCep(@PathVariable final String cep);

    @RequiredArgsConstructor
    @ToString
    @Getter
    final class ViaCepResponse {
        private final String cep;
        private final String logradouro;
        private final String complemento;
        private final String bairro;
        private final String localidade;
        private final String uf;
        @JsonProperty("ibge")
        private final String codigoIbgeUf;
        private final boolean erro;
    }

}
