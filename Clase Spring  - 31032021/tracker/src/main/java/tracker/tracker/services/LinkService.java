package tracker.tracker.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.dto.LinkResponseDTO;
import tracker.tracker.exceptions.InvalidUrlException;
import tracker.tracker.exceptions.LinkNotFoundException;
import tracker.tracker.repositories.LinkRepository;
import tracker.tracker.repositories.LinkRepositoryImpl;

public interface LinkService {

    LinkResponseDTO agregar(String link, String pwd);
    String redireccion(int id) throws LinkNotFoundException;
    ResponseEntity metricas(int id);
    void invalidar(int id) throws LinkNotFoundException;
    boolean consultaLinks(int id);
    boolean validatePwd(int id, String pwd);

}
