package co.com.sofka.model.viaje;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Viaje {
    private Integer idViaje;
    private String tipoviaje;
    private String nombreViaje;

}
