package store.respository;

import store.dto.ArticleDTO;
import store.exceptions.ArticleNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ArticleRepository {

    List<ArticleDTO> getAll();
    List<ArticleDTO> getByCategory(String category);
    List<ArticleDTO> getByFilters(Map<String,String> params);
    boolean modifyStock(List<ArticleDTO> articles) throws FileNotFoundException;
    double totalPurchase (List<ArticleDTO> articleDTOList) throws ArticleNotFoundException;
    ArticleDTO getById(int id) throws ArticleNotFoundException;

}
