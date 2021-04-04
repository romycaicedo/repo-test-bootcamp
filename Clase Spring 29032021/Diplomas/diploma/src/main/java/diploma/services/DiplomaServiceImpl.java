package diploma.services;

import diploma.dto.AsignaturaDTO;
import diploma.dto.DiplomaDTO;
import diploma.dto.EstudianteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomaServiceImpl implements DiplomaService{

@Override
    public DiplomaDTO generarDiploma(EstudianteDTO estudiante){
        DiplomaDTO diploma = new DiplomaDTO();
        diploma.setPromedio(Math.round(calcularPromedio(estudiante.asignaturas)));
        if(calcularPromedio(estudiante.asignaturas) > 9)
            diploma.setMensaje("Felicidades " + estudiante.nombre + " por su excelencia academica");
        else
            diploma.setMensaje("Felicidades");
        diploma.setEstudiante(estudiante);
        return diploma;

    }

    private Double calcularPromedio(List<AsignaturaDTO> asignaturas){
        double avg = 0;
        for (int i =0; i<asignaturas.size();i++){
           avg = avg + asignaturas.get(i).nota;
        }
        double result = avg /asignaturas.size();

        return result;
    }
}
