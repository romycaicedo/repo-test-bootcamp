package store.respository;

import store.dto.ArticleDTO;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ArticleRepository {

    List<ArticleDTO> getAll();
    List<ArticleDTO> getByCategory(String category);
    List<ArticleDTO> getByFilters(Map<String,String> params);
    boolean modifyStock(List<ArticleDTO> articles) throws FileNotFoundException;
    List<ArticleDTO> stockQuery(List<ArticleDTO>  articleDTOList);
    List<ArticleDTO> articleQuery(List<ArticleDTO> articleDTO);
    double totalPurchase (List<ArticleDTO> articleDTOList);

}
