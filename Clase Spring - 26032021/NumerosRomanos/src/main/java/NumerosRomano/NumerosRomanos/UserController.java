package NumerosRomano.NumerosRomanos;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/numeros")
public class UserController {

    //private final AtomicLong contador = new AtomicLong();

    @GetMapping("/test")
    public NumerosRomanos convetir(@RequestParam(value = "num", defaultValue = "uno")int num){
       NumerosRomanos nm = new NumerosRomanos();
       //nm.setNumero(num);
       String res  = new NumerosRomanos().conversion(num);
       return new NumerosRomanos("El valor en romanos es: "+ res,num);
    }

}
