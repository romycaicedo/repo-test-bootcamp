package calorias.repositories;

import calorias.dto.IngredientesDto;

public interface IngredientesRepository {
    IngredientesDto findByName(String name);
}
