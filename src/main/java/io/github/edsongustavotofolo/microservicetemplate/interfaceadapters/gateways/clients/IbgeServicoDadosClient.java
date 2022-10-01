package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ibge-servico-dados", url = "https://servicodados.ibge.gov.br/api/v1/localidades")
public interface IbgeServicoDadosClient {

    @GetMapping("/estados/{id}/municipios?orderBy=nome")
    List<IbgeMunicipio> buscarMunicipiosPorEstadoId(@PathVariable final Integer id);

    @RequiredArgsConstructor
    @Getter
    final class IbgeMunicipio {
        private final String id;
        private final String nome;
    }

    @GetMapping("/estados?orderBy=nome")
    List<IbgeEstado> buscarTodosOsEstados();

    @RequiredArgsConstructor
    @Getter
    final class IbgeEstado {
        private final Integer id;
        private final String sigla;
        private final String nome;
    }

    @GetMapping("/paises?orderBy=nome")
    List<IbgePais> buscarTodosOsPaises();

    @RequiredArgsConstructor
    @Getter
    final class IbgePaisId {
        @JsonProperty("M49")
        private final String m49;
        @JsonProperty("ISO-ALPHA-2")
        private final String isoAlpha2;
        @JsonProperty("ISO-ALPHA-3")
        private final String isoAlpha3;
    }

    @RequiredArgsConstructor
    @Getter
    final class IbgePais {
        private final IbgePaisId id;
        private final String nome;
    }

}
