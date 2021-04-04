package calorias.services;

import calorias.dto.IngredientesDto;
import calorias.dto.PlatoComidaDTO;
import calorias.dto.ResponseDto;
import calorias.exceptions.IngredientNotFoundException;

import java.util.List;


public interface CalculadoraService {
    public ResponseDto calcularCalorias(PlatoComidaDTO platoComidaDTO) throws IngredientNotFoundException;
    List<ResponseDto> calcularAll(List<PlatoComidaDTO> dishes);
}
