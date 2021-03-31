package tracker.tracker.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.repositories.LinkRepository;
import tracker.tracker.repositories.LinkRepositoryImpl;

public interface LinkService {

    ResponseEntity agregar(String link);
    String redireccion(int id);

}
