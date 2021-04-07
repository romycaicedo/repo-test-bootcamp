package store.service;

import store.dto.ArticleDTO;

import java.util.List;
import java.util.Map;

public interface ArticleService {
     List<ArticleDTO> getAll();
     List<ArticleDTO> getByCategory(String category);
     List<ArticleDTO> getByFilters(Map<String,String> params) throws NoSuchMethodException;
}
