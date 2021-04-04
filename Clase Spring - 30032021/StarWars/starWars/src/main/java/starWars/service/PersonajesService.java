package starWars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starWars.dto.PersonajesDTO;
import starWars.repository.PersonajesRepository;

import java.util.List;

@Service
public class PersonajesService {

    private PersonajesRepository personajesRepository;


    public PersonajesService(PersonajesRepository personajesRepository){
        this.personajesRepository = personajesRepository;
    }

    public List<PersonajesDTO> findByWord(String word){
        return personajesRepository.findByWord(word);
    }


}
