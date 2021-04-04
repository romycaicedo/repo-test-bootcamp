package diploma.services;

import diploma.dto.DiplomaDTO;
import diploma.dto.EstudianteDTO;

public interface DiplomaService {

    DiplomaDTO generarDiploma(EstudianteDTO estudiante);

}
