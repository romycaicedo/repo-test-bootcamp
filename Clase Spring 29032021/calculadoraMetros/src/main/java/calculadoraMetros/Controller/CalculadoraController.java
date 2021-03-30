package calculadoraMetros.Controller;


import calculadoraMetros.Service.CalculadoraService;
import calculadoraMetros.dto.CasaDTO;
import calculadoraMetros.dto.HabitacionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {


    @PostMapping("/casa")
    public ResponseEntity calcularArea(@RequestBody CasaDTO casa){
        double res;
        CalculadoraService calcular = new CalculadoraService();
        res = calcular.calcularArea(casa.getHabitaciones());
        return new ResponseEntity<>("{area:"+ res +"}", HttpStatus.OK);
    }
    @PostMapping("/valorcasa")
    public ResponseEntity valorCasa(@RequestBody CasaDTO casa){
        double res;
        CalculadoraService calcular = new CalculadoraService();
        res = calcular.valorCasa(calcular.calcularArea(casa.getHabitaciones()));
        return new ResponseEntity<>("{area:"+ res +"}", HttpStatus.OK);
    }

    @PostMapping("/maxhabitacion")
    public ResponseEntity habitacionMax(@RequestBody CasaDTO casa){
        HabitacionDTO res;
        CalculadoraService calcular = new CalculadoraService();
        res = calcular.obtenerHabitacionMax(casa.getHabitaciones());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/metroscuadrados")
    public ResponseEntity obtenerMetros2(@RequestBody CasaDTO casa){
        double res[]= new double[casa.getHabitaciones().size()];
        CalculadoraService calcular = new CalculadoraService();
        res = calcular.obtenerMetro2(casa.getHabitaciones());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
