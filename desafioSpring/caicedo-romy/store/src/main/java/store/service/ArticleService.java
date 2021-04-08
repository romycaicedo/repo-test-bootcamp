package store.service;

import store.dto.*;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.FiltersException;
import store.exceptions.StockOutOfBoundsException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ArticleService {
     List<ArticleDTO> getAll();
     List<ArticleDTO> getByCategory(String category);
     List<ArticleDTO> getByFilters(Map<String,String> params) throws FiltersException;
     PurchaseDTO purchaseRequest(ArticlesDTO articleDTOList) throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException;
     CartDTO cartRequest() throws FileNotFoundException, ArticleNotFoundException;
     List<PurchaseDTO> addPurchase(PurchaseDTO purchase);
     void modifyStock() throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException;
}
