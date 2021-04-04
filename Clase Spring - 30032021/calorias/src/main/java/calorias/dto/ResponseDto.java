package calorias.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ResponseDto  extends PlatoComidaDTO{
    private int caloriasTotales;
    private IngredientesDto ingMayorCalorias;

    public ResponseDto(PlatoComidaDTO dish) {
        this.setIngredientes(dish.getIngredientes());
        this.setNombre(dish.getNombre());
    }
}
