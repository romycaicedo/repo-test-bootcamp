package calorias.repositories;

import calorias.dto.IngredientesDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientesRepositoryImpl implements IngredientesRepository {

    @Override
    public IngredientesDto findByName(String name) {

        List<IngredientesDto> ingredientes = loadDataBase();
        IngredientesDto result = null;
        if(ingredientes != null){
            Optional<IngredientesDto> item = ingredientes.stream()
                    .filter(ingredientes1 -> ingredientes1.getName().equals(name))
                    .findFirst();
            if(item.isPresent()){
                result = item.get();
            }

        }
        return result;
    }



    private List<IngredientesDto> loadDataBase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:Food.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredientesDto>> typeRef = new TypeReference<>() {};
        List<IngredientesDto> ingredientes = null;

        try {
            ingredientes = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return ingredientes;
    }


}
