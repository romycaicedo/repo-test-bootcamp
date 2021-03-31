package tracker.tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.services.LinkService;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService link;

    @PostMapping("/agregar")
    public ResponseEntity agregar(@RequestBody String link){
        return this.link.agregar(link);
    }
    /*@GetMapping("/redirect")
    public ResponseEntity redireccionar(){

    }
    @GetMapping("/metricas")
    public ResponseEntity metricas(){

    }
    @PostMapping("/eliminar")
    public ResponseEntity eliminar(){

    }*/
    @GetMapping("/{linkId}")
    public ModelAndView Redirect(@PathVariable Integer linkId)  {
        return new ModelAndView("redirect:http://"+ link.redireccion(linkId));
    }
}
