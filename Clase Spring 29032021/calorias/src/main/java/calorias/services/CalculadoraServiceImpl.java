package calorias.services;

import calorias.dto.Ingredientes;
import calorias.dto.IngredientesDto;
import calorias.dto.PlatoComida;
import calorias.dto.ResponseDto;
import calorias.exceptions.IngredientNotFoundException;
import calorias.repositories.IngredientesRepository;
import calorias.repositories.IngredientesRepositoryImpl;
import calorias.dto.Ingredientes;
import calorias.repositories.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{

    @Autowired
    private IngredientesRepository ingredientesRepository;

    @Override
    public Integer caloriasTotales(List<Ingredientes> ingredientes) throws IngredientNotFoundException {
        int total = 0;
        int calorias;

        for (Ingredientes i: ingredientes) {
            if(ingredientesRepository.findByName(i.getNombre()) != null) {
                calorias = ingredientesRepository.findByName(i.getNombre()).getCalories();
                total = total + i.getPeso() * calorias;
            }
            else
                throw new IngredientNotFoundException(i.getNombre(),"Not Found Ingredient", HttpStatus.BAD_REQUEST);


        }
        return total;
    }

    @Override
    public List<IngredientesDto> caloriasPorIng(List<Ingredientes> ingredientes) {
        List<IngredientesDto> result = new ArrayList<IngredientesDto>();
        for (Ingredientes i:ingredientes) {
            IngredientesDto list = null;
            list = ingredientesRepository.findByName(i.getNombre());
            result.add(list);
        }
        return result;
    }

    @Override
    public IngredientesDto ingMayorCalorias(List<Ingredientes> ingredientes) {
        int maxCalories = 0;
        IngredientesDto ingMayorCalorias = null;

        for (Ingredientes i: ingredientes) {
            int compare = ingredientesRepository.findByName(i.getNombre()).getCalories();

            if(maxCalories == 0){
                maxCalories= compare;
            }
            if(maxCalories < compare){
                maxCalories = compare;
                ingMayorCalorias = ingredientesRepository.findByName(i.getNombre());
            }
        }

        return ingMayorCalorias;
    }

    public ResponseDto getResponse(PlatoComida plato) throws IngredientNotFoundException {

        ResponseDto res = new ResponseDto();
        res.setCaloriasTotales(caloriasTotales(plato.getIngredientes()));
        res.setCaloriasInd(caloriasPorIng(plato.getIngredientes()));
        res.setIngMayorCalorias(ingMayorCalorias(plato.getIngredientes()));

        return res;
    }
}