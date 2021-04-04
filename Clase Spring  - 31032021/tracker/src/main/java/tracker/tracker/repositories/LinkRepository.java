package tracker.tracker.repositories;

import tracker.tracker.dto.LinkDto;

public interface LinkRepository {

       LinkDto agregarDatos(String link, String pwd);
       LinkDto eliminarDatos(int id);
       LinkDto getDatos(int id);

}
