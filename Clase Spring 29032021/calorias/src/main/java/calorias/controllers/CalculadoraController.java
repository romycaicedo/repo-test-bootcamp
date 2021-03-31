package calorias.controllers;

import calorias.dto.PlatoComida;
import calorias.dto.ResponseDto;
import calorias.exceptions.IngredientNotFoundException;
import calorias.services.CalculadoraService;
import calorias.services.CalculadoraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {
    @Autowired
    private CalculadoraServiceImpl calculadora ;

    @PostMapping("/calcular")
    public ResponseDto calcularCalorias(@RequestBody PlatoComida plato) throws IngredientNotFoundException {

     return calculadora.getResponse(plato);
    }

    public ResponseDto
}
