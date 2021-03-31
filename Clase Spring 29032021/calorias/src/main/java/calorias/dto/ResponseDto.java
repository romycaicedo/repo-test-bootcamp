package calorias.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDto {
    private int caloriasTotales;
    private List<IngredientesDto> caloriasInd;
    private IngredientesDto ingMayorCalorias;
}
