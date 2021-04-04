package diploma.controllers;

import diploma.dto.DiplomaDTO;
import diploma.dto.EstudianteDTO;
import diploma.services.DiplomaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiplomaController {
    private  final DiplomaService diplomaService;

    public DiplomaController(DiplomaService diplomaService) {
        this.diplomaService = diplomaService;
    }

    @PostMapping("/diploma")
    public DiplomaDTO analyzeNotes(@RequestBody EstudianteDTO notes){
        return diplomaService.generarDiploma(notes);
    }
}
