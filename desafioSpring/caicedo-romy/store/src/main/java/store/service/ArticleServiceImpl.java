package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.ArticleDTO;
import store.respository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements  ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    public List<ArticleDTO> getAll(){
        return articleRepository.getAll();
    }

    @Override
    public List<ArticleDTO> getByCategory(String category) {
        return articleRepository.getByCategory(category);

    }
    public List<ArticleDTO> getByFilters(Map<String,String> params) throws NoSuchMethodException {
        return articleRepository.getByFilters(params);
    }




}
