package demo.romanos.Controller;

import demo.romanos.Entities.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/hi")
    public Greeting sayHi(@RequestParam(value = "name", defaultValue = "World") String name){

        return new Greeting(
                contador.incrementAndGet(),
                "Hi, " + name + " how are you?",
                this.getClass().getSimpleName());
    }
}
