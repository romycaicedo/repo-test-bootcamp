package diploma.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DiplomaDTO {
    public String mensaje;
    public double promedio;
    public EstudianteDTO estudiante;


}
