package calorias.services;

import calorias.dto.IngredientesDto;
import calorias.dto.PlatoComidaDTO;
import calorias.dto.ResponseDto;
import calorias.exceptions.IngredientNotFoundException;
import calorias.repositories.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{

    @Autowired
    private IngredientesRepository ingredientesRepository;

    public CalculadoraServiceImpl(IngredientesRepository ingredientRepository) {
        this.ingredientesRepository = ingredientRepository;
    }


    @Override
    public ResponseDto calcularCalorias(PlatoComidaDTO dish) {
        ResponseDto response = new ResponseDto(dish);
        int total = 0;
        int maxCalories = 0;
        for (IngredientesDto ingredient : response.getIngredientes()) {
            caloriasPorIngrediente(ingredient);
            total += ingredient.getCalories();
            if (ingredient.getCalories() > maxCalories) {
                response.setCaloriasTotales(ingredient.getCalories());
                maxCalories = ingredient.getCalories();
                response.setIngMayorCalorias(ingredient);
            }
        }
        response.setCaloriasTotales(total);
        return response;
    }



    @Override
    public List<ResponseDto> calcularAll(List<PlatoComidaDTO> dishes) {
        List<ResponseDto> result = new ArrayList<>();
        for (PlatoComidaDTO dish : dishes) {
            result.add(this.calcularCalorias(dish));
        }
        return result;
    }

    private void caloriasPorIngrediente(IngredientesDto ingredient) {
        ingredient.setCalories(0);
        IngredientesDto ingredientFromRepository = ingredientesRepository.findByName(ingredient.getName());
        if (ingredientFromRepository != null)
            ingredient.setCalories((int) (ingredient.getPeso() * ingredientFromRepository.getCalories() / 100.f));
    }


}