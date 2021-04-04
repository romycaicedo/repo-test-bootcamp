package starWars.repository;

import starWars.dto.PersonajesDTO;

import java.util.List;

public interface PersonajesRepository {

    List<PersonajesDTO> findByWord(String word);

}
