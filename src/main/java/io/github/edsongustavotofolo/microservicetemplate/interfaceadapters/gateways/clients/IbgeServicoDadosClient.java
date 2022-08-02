package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ibge-servico-dados",
        url = "https://servicodados.ibge.gov.br/api/v1/localidades")
public interface IbgeServicoDadosClient {

    @GetMapping("/estados/{id}/municipios?orderBy=nome")
    List<IbgeMunicipio> buscarMunicipiosPorEstadoId(@PathVariable Integer id);

    @Getter
    final class IbgeMunicipio {
        private final String id;
        private final String nome;

        public IbgeMunicipio(String id, String nome) {
            this.id = id;
            this.nome = nome;
        }
    }

    @GetMapping("/estados?orderBy=nome")
    List<IbgeEstado> buscarTodosOsEstados();

    @Getter
    final class IbgeEstado {
        private final Integer id;
        private final String sigla;
        private final String nome;

        public IbgeEstado(Integer id, String sigla, String nome) {
            this.id = id;
            this.sigla = sigla;
            this.nome = nome;
        }
    }

    @GetMapping("/paises?orderBy=nome")
    List<IbgePais> buscarTodosOsPaises();

    @Getter
    final class IbgePaisId {
        @JsonProperty("M49")
        private final String m49;
        @JsonProperty("ISO-ALPHA-2")
        private final String isoAlpha2;
        @JsonProperty("ISO-ALPHA-3")
        private final String isoAlpha3;

        public IbgePaisId(String m49, String isoAlpha2, String isoAlpha3) {
            this.m49 = m49;
            this.isoAlpha2 = isoAlpha2;
            this.isoAlpha3 = isoAlpha3;
        }
    }

    @Getter
    final class IbgePais {
        private final IbgePaisId id;
        private final String nome;

        public IbgePais(IbgePaisId id, String nome) {
            this.id = id;
            this.nome = nome;
        }
    }

}
