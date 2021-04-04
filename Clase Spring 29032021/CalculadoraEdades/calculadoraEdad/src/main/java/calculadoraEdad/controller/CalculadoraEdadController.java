package calculadoraEdad.controller;

import calculadoraEdad.dto.EdadDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

@RestController
public class CalculadoraEdadController {

  @GetMapping("/{dia}/{mes}/{año}")
    public EdadDTO obtenerEdad(@PathVariable("dia") int dia,@PathVariable("mes") int mes, @PathVariable("año") int año){
    EdadDTO edadDTO = new EdadDTO();
    edadDTO.setFecha(dia + "/" + mes + "/" + año);
    edadDTO.setEdad(calcularEdad(dia,mes,año));
    return  edadDTO;

  }

  private int calcularEdad(int dia, int mes, int año){
      Period edad;
      try{
          LocalDate fecha = LocalDate.of(año,mes,dia);
          edad = Period.between(fecha, LocalDate.now());
          return edad.getYears();
      } catch(Exception e ){
          return 0;
      }

  }


}
