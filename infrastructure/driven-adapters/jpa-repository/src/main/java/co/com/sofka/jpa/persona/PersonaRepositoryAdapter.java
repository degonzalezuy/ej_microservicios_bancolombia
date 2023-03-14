package co.com.sofka.jpa.persona;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.persona.Persona;
import co.com.sofka.model.persona.gateways.PersonaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static co.com.sofka.jpa.convertidor.Convertidor.*;

@Repository
public class PersonaRepositoryAdapter extends AdapterOperations<Persona, PersonaData,
        Integer, PersonaDataRepository> implements PersonaRepository {
    public PersonaRepositoryAdapter(PersonaDataRepository repository, ObjectMapper mapper){
        super(repository, mapper, d -> mapper.mapBuilder(d, Persona.PersonaBuilder.class).build());

    }
    @Override
    public Flux<Persona> buscarPersonas() {
        List<PersonaData> personas = (List<PersonaData>) repository.findAll();

        if(!personas.isEmpty()){
            return Flux.fromIterable(convertirPersonasDataAPersonas(personas));
        }else {
            return Flux.empty();
        }
    }

    @Override
    public Mono<Persona> crearPersona(Persona persona) {

        if(persona != null){
            PersonaData nuevaPersonaData = convertirPersonaAPersonaData(persona);
            repository.save(nuevaPersonaData);
            return Mono.just(persona);
        }else {
            return Mono.empty();
        }

        /*
       Primer version
       PersonaData nuevaPersonaData = convertirPersonaAPersonaData(persona);

        nuevaPersonaData.setNombre(nuevaPersonaData.getNombre());
        nuevaPersonaData.setFechaNacimiento(nuevaPersonaData.getFechaNacimiento());
        nuevaPersonaData.setTipoSangre(nuevaPersonaData.getTipoSangre());
        nuevaPersonaData.setViajes(nuevaPersonaData.getViajes());
        repository.save(nuevaPersonaData);

        return Mono.just(persona);*/
    }

    @Override
    public Mono<Persona> buscarPersonaPorId(Integer id) {
        Optional<PersonaData> personaData = repository.findById(id);
        return personaData.map(data -> Mono.just(convertirPersonaDataAPersona(data)))
                .orElseGet(Mono::empty);
    }


}
