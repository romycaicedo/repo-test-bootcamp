package Controllers;

import Entities.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/hi")
    public Greeting sayHi(@RequestParam (value = "name", defaultValue = "World") String name){

        return new Greeting(contador.incrementAndGet(),"hi"+ name +"how are you?", this.getClass().getSimpleName());
    }
    @GetMapping("/bye/{name}")
    public Greeting bye (@PathVariable() String name){

        return new Greeting(contador.incrementAndGet(),"bye"+ name +"see ya", this.getClass().getSimpleName());
    }

}
