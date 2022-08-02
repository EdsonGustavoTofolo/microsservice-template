package io.github.edsongustavotofolo.microservicetemplate.infrastructure.configuration.database.repository;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    // TODO: ajustar para pegar o usuario logado
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("edson.tofolo");
        /*
        Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername);
         */
    }
}
