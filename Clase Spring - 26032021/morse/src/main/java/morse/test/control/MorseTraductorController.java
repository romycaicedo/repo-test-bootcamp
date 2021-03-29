package morse.test.control;

import morse.test.entities.MorseTraductor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MorseTraductorController {
    @GetMapping("/trad")
    public String traductor(@RequestParam(value = "message", defaultValue = ".--.") String message){
        //MorseTraductor mt = new MorseTraductor();
        String res = new MorseTraductor().decodificarMorse(message);
        return "El mensaje es:" + res;
    }

}