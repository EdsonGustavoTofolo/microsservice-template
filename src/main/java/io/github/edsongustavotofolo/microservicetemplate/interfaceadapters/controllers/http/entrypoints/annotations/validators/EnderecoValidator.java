package io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.validators;

import feign.FeignException;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.annotations.Endereco;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.controllers.http.entrypoints.v1.fornecedores.dtos.EnderecoRequest;
import io.github.edsongustavotofolo.microservicetemplate.interfaceadapters.gateways.clients.ViaCepClient;
import io.github.edsongustavotofolo.microservicetemplate.usecases.providers.CidadeProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
public class EnderecoValidator implements ConstraintValidator<Endereco, Object> {

    @Autowired
    private ViaCepClient cepClient;
    @Autowired
    private CidadeProvider cidadeProvider;

    @Override
    public void initialize(Endereco constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof EnderecoRequest endereco) {
            if (isNotBlank(endereco.getCep()) && isNotBlank(endereco.getUf()) && (endereco.hasCity())) {
                try {
                     return this.cepClient.buscarPorCep(endereco.getCep())
                            .map(viaCepResponse -> {
                                if (viaCepResponse.isErro()) {
                                    log.error("CEP informado não existe.");
                                    return false;
                                }

                                if (!endereco.getUf().equalsIgnoreCase(viaCepResponse.getUf())) {
                                    log.error("UF informada não confere com UF do CEP. UF informada: {}, UF CEP: {}", endereco.getUf(), viaCepResponse.getUf());
                                    return false;
                                }

                                return this.cidadeProvider.getById(endereco.getCidade())
                                        .map(cidade -> {
                                                    final var mesmaUf = endereco.getUf().equalsIgnoreCase(cidade.getEstado().getSigla());
                                                    if (!mesmaUf) {
                                                        log.error("UF informada não confere com UF da Cidade. UF informada: {}, UF cidade: {}", endereco.getUf(), cidade.getEstado().getSigla());
                                                    }
                                                    return mesmaUf;
                                                }
                                        ).orElse(false);
                            }).orElse(false);
                } catch (final FeignException.FeignClientException exception) {
                    log.error("Busca pelo cep " + endereco.getCep() + " falhou.", exception);
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
