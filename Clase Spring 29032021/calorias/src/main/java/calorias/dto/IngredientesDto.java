package calorias.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IngredientesDto {
    private String name;
    private int calories;
    private int peso;

}
