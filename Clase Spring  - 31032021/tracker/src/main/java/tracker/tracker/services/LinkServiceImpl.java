package tracker.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tracker.tracker.dto.LinkDto;
import tracker.tracker.dto.LinkResponseDTO;
import tracker.tracker.exceptions.InvalidUrlException;
import tracker.tracker.exceptions.LinkNotFoundException;
import tracker.tracker.repositories.LinkRepository;
import tracker.tracker.repositories.LinkRepositoryImpl;

import javax.servlet.http.HttpServletResponse;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository link;

    public LinkResponseDTO agregar(String link, String pwd){
        LinkDto dto = this.link.agregarDatos(link,pwd);
        LinkResponseDTO response =  new LinkResponseDTO();
        response.setLinkId(dto.id);
        return response;
    }

    public String redireccion(int id ) throws  LinkNotFoundException{
        int counter=1;
        String url = "";
            LinkDto link = this.link.getDatos(id);
            url = link.url;
            if (this.link.getDatos(id).metric == 0) {
                this.link.getDatos(id).setMetric(counter);
            } else {
                counter = this.link.getDatos(id).metric;
                this.link.getDatos(id).setMetric(counter + 1);
            }
        return url;

    }

    public ResponseEntity metricas(int id){
        LinkDto link = this.link.getDatos(id);
        int metric = link.metric;
        return new ResponseEntity("Amount of requests: " + metric+  " Link: " + link.url, HttpStatus.OK);
    }

    public void invalidar(int id) throws LinkNotFoundException {
        LinkDto dto = this.link.eliminarDatos(id);

    }

    public boolean consultaLinks(int linkId){
        boolean exists;
        if(this.link.getDatos(linkId) != null)
            exists = true;
        else
            exists = false;

        return exists;

    }

    public boolean validatePwd(int id, String pwd){
        boolean match;
        if(this.link.getDatos(id).contraseña.equals(pwd)){
            match = true;
        }
        else
            match = false;
        return match;
    }


}
