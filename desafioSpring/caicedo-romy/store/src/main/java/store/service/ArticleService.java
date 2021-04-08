package store.service;

import store.dto.ArticleDTO;
import store.dto.PurchaseDTO;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.StockOutOfBoundsException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ArticleService {
     List<ArticleDTO> getAll();
     List<ArticleDTO> getByCategory(String category);
     List<ArticleDTO> getByFilters(Map<String,String> params);
     PurchaseDTO purchaseRequest(List<ArticleDTO> articleDTOList) throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException;
}
