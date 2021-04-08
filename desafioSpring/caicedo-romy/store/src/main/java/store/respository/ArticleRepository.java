package store.respository;

import store.dto.ArticleDTO;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.StockOutOfBoundsException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ArticleRepository {

    List<ArticleDTO> getAll();
    List<ArticleDTO> getByCategory(String category);
    List<ArticleDTO> getByFilters(Map<String,String> params);
    void modifyStock(ArticleDTO articles) throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException;
    double totalPurchase (List<ArticleDTO> articleDTOList) throws ArticleNotFoundException;
    ArticleDTO getById(int id) throws ArticleNotFoundException;

}
