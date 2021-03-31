package calorias.services;

import calorias.dto.Ingredientes;
import calorias.dto.IngredientesDto;
import calorias.exceptions.IngredientNotFoundException;

import java.util.List;


public interface CalculadoraService {
    public Integer caloriasTotales(List<Ingredientes> ingredientes) throws IngredientNotFoundException;
    public List<IngredientesDto> caloriasPorIng(List<Ingredientes> ingredientes);
    public IngredientesDto ingMayorCalorias(List<Ingredientes> ingredientes);
}
