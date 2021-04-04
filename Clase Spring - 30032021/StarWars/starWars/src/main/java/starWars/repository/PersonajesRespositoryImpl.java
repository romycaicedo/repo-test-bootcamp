package starWars.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import starWars.dto.PersonajesDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonajesRespositoryImpl implements PersonajesRepository{

    @Override
    public List<PersonajesDTO> findByWord(String word){
        List<PersonajesDTO> personajes = loadDataBase();

        return personajes.stream().filter(personajesDTO -> matchWith(word,personajesDTO)).collect(Collectors.toList());


    }
    private boolean matchWith(String query, PersonajesDTO characterDTO) {
        return characterDTO.getName().toUpperCase().contains(query.toUpperCase());
    }


    private List<PersonajesDTO> loadDataBase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:starwars.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonajesDTO>> typeRef = new TypeReference<>() {};
        List<PersonajesDTO> personajes = null;

        try {
            personajes = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return personajes;
    }
}
