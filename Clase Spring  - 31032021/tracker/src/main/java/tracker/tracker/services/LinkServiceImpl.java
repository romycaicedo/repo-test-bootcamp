package tracker.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.repositories.LinkRepository;

import javax.servlet.http.HttpServletResponse;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository link;

    public ResponseEntity agregar(String link){
        LinkDto dto = this.link.agregarDatos(link);
        return new ResponseEntity("Tu id es: " +dto. getId(), HttpStatus.OK);
    }

    public String redireccion(int id ) {
        String url = "";
        try {
            LinkDto link = this.link.getDatos(id);
            url = link.url;
        }catch (Exception e ){
            e.printStackTrace();
        }

        return url;
    }


}
