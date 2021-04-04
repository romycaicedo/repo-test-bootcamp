package tracker.tracker.repositories;

import org.springframework.stereotype.Repository;
import tracker.tracker.dto.LinkDto;

import java.util.HashMap;
import java.util.List;

@Repository
public class LinkRepositoryImpl implements LinkRepository{

    HashMap<Integer,LinkDto> linkDtos = new HashMap<Integer,LinkDto>();
    int contador = 0;
    @Override
    public LinkDto agregarDatos(String link, String pwd){
        LinkDto linkDto = new LinkDto(pwd,contador,link,0);
        linkDtos.put(linkDto.id, linkDto);
        contador++;
        return linkDto;
    }
    @Override
    public LinkDto eliminarDatos(int id){
        LinkDto dto = linkDtos.get(id);
        linkDtos.remove(id);
        return dto;
    }
    @Override
    public LinkDto getDatos(int id){
        return linkDtos.get(id);
    }
}
