package co.com.sofka.model.persona.gateways;

import reactor.core.publisher.Mono;

public interface PersonaPublicador {
    Mono<String> publicarPersonasViajeras();
}
