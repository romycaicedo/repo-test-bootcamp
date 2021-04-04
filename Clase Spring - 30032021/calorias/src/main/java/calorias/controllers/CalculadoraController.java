package calorias.controllers;


import calorias.dto.PlatoComidaDTO;
import calorias.dto.ResponseDto;
import calorias.exceptions.IngredientNotFoundException;
import calorias.services.CalculadoraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculadoraController {
    @Autowired
    private CalculadoraServiceImpl calculadora ;

    @PostMapping("/calcular")
    public ResponseDto calcularCalorias(@RequestBody PlatoComidaDTO plato) throws IngredientNotFoundException {
        return calculadora.calcularCalorias(plato);
    }

    @PostMapping("/calcularPlato")
    public List<ResponseDto> calculate(@RequestBody List<PlatoComidaDTO> platosList) throws IngredientNotFoundException {
        return calculadora.calcularAll(platosList);
    }
}
