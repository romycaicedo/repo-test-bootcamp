package calorias.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PlatoComida {

    private String nombre;
    private List<Ingredientes> ingredientes;


}
