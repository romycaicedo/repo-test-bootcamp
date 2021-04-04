package calorias.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class PlatoComidaDTO {
    private String nombre;
    private List<IngredientesDto> ingredientes;


}




