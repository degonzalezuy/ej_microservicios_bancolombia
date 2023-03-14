package co.com.sofka.jpa.convertidor;

import co.com.sofka.jpa.persona.PersonaData;
import co.com.sofka.jpa.viaje.ViajeData;
import co.com.sofka.model.persona.Persona;
import co.com.sofka.model.viaje.Viaje;

import java.util.List;
import java.util.stream.Collectors;

public class Convertidor {
    private Convertidor(){
        throw new IllegalStateException("Utility class");
    }

    public static Viaje convertirViajeDataAViaje(ViajeData data){
        return Viaje.builder()
                .idViaje(data.getIdViaje())
                .tipoviaje(data.getTipoViaje())
                .nombreViaje(data.getNombreViaje())
                .build();
    }

    public static List<Viaje> convertirViajesDataAViajes(List<ViajeData> viajes){
        return viajes.stream()
                .map(Convertidor::convertirViajeDataAViaje)
                .collect(Collectors.toList());
    }

    public static Persona convertirPersonaDataAPersona(PersonaData data){

        return Persona.builder()
                .cedula(data.getIdPersona())
                .nombre(data.getNombre())
                .fechaNacimiento(data.getFechaNacimiento())
                .tipoSangre(data.getTipoSangre())
                .viajes(convertirViajesDataAViajes(data.getViajes()))
                .build();
    }

    public static List<Persona>convertirPersonasDataAPersonas(List<PersonaData> personas){
        return personas.stream()
                .map(Convertidor::convertirPersonaDataAPersona)
                .collect(Collectors.toList());
    }

    public static ViajeData convertiViajeAViajeData(Viaje dominio){
        ViajeData viajeData = new ViajeData();
        viajeData.setIdViaje(dominio.getIdViaje());
        viajeData.setNombreViaje(dominio.getNombreViaje());
        viajeData.setTipoViaje(dominio.getTipoviaje());

        return viajeData;
    }

    public static List<ViajeData> convertirViajesAViajesData(List<Viaje>viajes){
        return viajes.stream()
                .map(Convertidor::convertiViajeAViajeData)
                .collect(Collectors.toList());
    }

    public static PersonaData convertirPersonaAPersonaData(Persona dominio){
        PersonaData personaData= new PersonaData();
        personaData.setIdPersona(dominio.getCedula());
        personaData.setNombre(dominio.getNombre());
        personaData.setFechaNacimiento(dominio.getFechaNacimiento());
        personaData.setTipoSangre(dominio.getTipoSangre());
        personaData.setViajes(convertirViajesAViajesData(dominio.getViajes()));
        return personaData;
    }
}
