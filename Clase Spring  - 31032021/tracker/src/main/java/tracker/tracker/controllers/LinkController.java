package tracker.tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tracker.tracker.dto.ErrorDTO;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.dto.LinkResponseDTO;
import tracker.tracker.exceptions.InvalidPasswordException;
import tracker.tracker.exceptions.InvalidUrlException;
import tracker.tracker.exceptions.LinkNotFoundException;
import tracker.tracker.repositories.LinkRepositoryImpl;
import tracker.tracker.services.LinkService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService link;

    @PostMapping("/agregar")
    public ResponseEntity agregar(@RequestBody LinkDto url) throws InvalidUrlException {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
        Pattern pattern= Pattern.compile(regex);
        Matcher m = pattern.matcher(url.getUrl());
        if (!m.matches())throw new InvalidUrlException(url.getUrl());
        LinkResponseDTO linkID = link.agregar(url.getUrl(),url.getContraseña());
;        return new ResponseEntity(linkID,HttpStatus.OK);
    }

    @GetMapping("/metrics/{linkId}")
    public ResponseEntity metricas(@PathVariable Integer linkId) throws LinkNotFoundException {
        if(link.consultaLinks(linkId)){
            return this.link.metricas(linkId);
        }
        else
            throw new LinkNotFoundException(linkId.toString());

    }

    @GetMapping("/invalidate/{linkId}")
    public ResponseEntity eliminar(@PathVariable Integer linkId) throws LinkNotFoundException {
        if(link.consultaLinks(linkId)){
            link.invalidar(linkId);
        }else
            throw new LinkNotFoundException(linkId.toString());

        return new ResponseEntity("Link Successfully Invalidates", HttpStatus.OK);

    }
    @GetMapping("/{linkId}/{pwd}")
    public ResponseEntity<Object> redirectToExternalUrl(@PathVariable("linkId") Integer linkId,@PathVariable("pwd") String pwd) throws URISyntaxException, InvalidUrlException, LinkNotFoundException, InvalidPasswordException {
        if(link.consultaLinks(linkId)) {
            if(link.validatePwd(linkId, pwd)) {
                String textUri = link.redireccion(linkId);
                URI yahoo = new URI(textUri);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(yahoo);
                return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
            }
            else {
                throw  new InvalidPasswordException();
            }
        }
        else
            throw new LinkNotFoundException(linkId.toString());

    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity badUrlSent(InvalidUrlException n)
    {
        ErrorDTO error= new ErrorDTO();
        error.setName("Invalid Url Sent");
        error.setMessage("the url provided : " + n.getMessage() + " is not a valid URL like 'http://google.com' or doesn't exist in our repository");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity linkNotFound(LinkNotFoundException n)
    {
        ErrorDTO error= new ErrorDTO();
        error.setName("Id Link does not exist");
        error.setMessage("the id provided : " + n.getMessage() + " doesn't exist");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity invalidPwd()
    {
        ErrorDTO error= new ErrorDTO();
        error.setName("Invalid Password");
        error.setMessage("the password provided don't match with repository" );
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
