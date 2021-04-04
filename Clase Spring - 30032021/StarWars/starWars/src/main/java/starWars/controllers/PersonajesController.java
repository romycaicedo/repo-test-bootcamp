package starWars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import starWars.dto.PersonajesDTO;
import starWars.service.PersonajesService;

import java.util.List;

@RestController
public class PersonajesController {

    @Autowired
    private PersonajesService personajesService;

    @GetMapping("/{word}")
    public List<PersonajesDTO> findByWord(@PathVariable String word){
        return personajesService.findByWord(word);
    }
}
