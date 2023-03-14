package co.com.sofka.api;

import co.com.sofka.model.persona.Persona;
import co.com.sofka.usecase.persona.PersonaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonaRest {
    private final PersonaUseCase personaUseCase;

    @GetMapping("/personas")
    public Flux<Persona> buscarPersonasParaViajar(){
        return personaUseCase.buscarPersonas();
    }

    @GetMapping("/personas/{id}")
    public Mono<Persona> buscarPersonas(@PathVariable Integer id){
        return personaUseCase.buscarPersonaPorId(id);
    }

    @PostMapping("/personas")
    public Mono<Persona> guardarPersona(@RequestBody Persona persona){
        return personaUseCase.crearPersona(persona);

    }

    @GetMapping("/buscar-por-comando")
    public Mono<String> buscarPeronsasPorComando(){
        return personaUseCase.buscarPersonasPorComando();
    }
}
