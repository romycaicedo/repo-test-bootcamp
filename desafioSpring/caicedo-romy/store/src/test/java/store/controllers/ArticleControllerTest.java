package store.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import store.dto.ArticleDTO;
import store.respository.ArticleRepository;
import store.service.ArticleService;
import store.service.ArticleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleRepository articleRepository;


    @MockBean
    private ArticleService articleService;

    public void setup(){
        initMocks(this);
        articleService = new ArticleServiceImpl();
    }

    @Test
    public void shouldGetAllArticles() throws Exception{

        assertNotNull(articleRepository);
        List<ArticleDTO> articles = createArticles();
        when(articleRepository.getAll()).thenReturn(articles);
        MvcResult  result= this.mockMvc.perform(get("/api/v1/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        assertNotNull(res);
        assertEquals(4,articles.size());
    }

    @Test
    public void shouldGetAllArticlesByCategory() throws Exception{

        assertNotNull(articleRepository);
        List<ArticleDTO> articles = createArticlesByCategory();
        when(articleRepository.getByCategory("Herramientas")).thenReturn(articles);
        MvcResult  result= this.mockMvc.perform(get("/api/v1/articles?category=Herramientas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        assertNotNull(res);
        assertEquals(2,articles.size());
    }

    private List<ArticleDTO> createArticles(){
        List<ArticleDTO> test = new ArrayList<>();
        test.add(new ArticleDTO(1,"Desmalezadora","Herramientas","Makita","$9.600",5,"SI","****"));
        test.add(new ArticleDTO(2,"Taladro","Herramientas","Black & Decker","$9.600",3,"NO","****"));
        test.add(new ArticleDTO(3,"Camisa","Deportes","Adidas","$9.850",4,"NO","***"));
        test.add(new ArticleDTO(2,"Zapatos","Deportes","Nike","$13.600",2,"NO","*****"));
        return test;
    }

    private List<ArticleDTO> createArticlesByCategory(){
        List<ArticleDTO> test = new ArrayList<>();
        test.add(new ArticleDTO(1,"Desmalezadora","Herramientas","Makita","$9.600",5,"SI","****"));
        test.add(new ArticleDTO(2,"Taladro","Herramientas","Black & Decker","$9.600",3,"NO","****"));
        return test;
    }
}
