package store.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import store.dto.ArticleDTO;
import store.respository.ArticleRepository;
import store.service.ArticleService;
import store.service.ArticleServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    private final String ORDERED_BY_PRESTIGE = "src/test/resources/static/allProductsOrderedByPrestige.json";
    private final String FILTERED_BY_CATEGORY = "src/test/resources/static/filteredByCategoryProducts.json";
    private final String FILTERED_BY_CATEGORY_AND_PRICE = "src/test/resources/static/productsFilteredByCategoryAndProducts.json";
    private final String ORDERED_ALPHABETICALLY = "src/test/resources/static/productsOrderedAlphabetically.json";
    private final String ORDERED_ANTI_ALPHABETICALLY = "src/test/resources/static/productsOrderedAntiAlphabetically.json";
    private final String ORDERED_BY_PRICE_DESC = "src/test/resources/static/productsOrderedByPriceDesc.json";
    private final String ORDERED_BY_PRICE_ASC = "src/test/resources/static/productsOrderedByPriceAsc.json";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleController articleController;

    @MockBean
    private ArticleService articleService;

    public void setup(){
        initMocks(this);
        articleService = new ArticleServiceImpl();
    }

    @Test
    public void shouldGetAllArticles() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        List<ArticleDTO> articles = createArticles();
        when(articleService.getAll()).thenReturn(articles);
        MvcResult  result= this.mockMvc.perform(get("/api/v1/articles").contentType(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();
        String json =result.getResponse().getContentAsString();
        List<ArticleDTO> actual = objectMapper.readValue(json, new TypeReference<>(){});
        assertIterableEquals(articles,actual);

    }

    @Test
    public void shouldGetAllArticlesByCategory() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        //mock
        List<ArticleDTO> articles = objectMapper.readValue(new File(FILTERED_BY_CATEGORY), new TypeReference<>() {});
        when(articleService.getByCategory("Herramientas")).thenReturn(articles);
        //Mapping
        MvcResult  result= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/articles?category=Herramientas").accept(MediaType.ALL))
                .andExpect(status().isOk()).andReturn();
        List<ArticleDTO> responseArticles =
            objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
        assertEquals(responseArticles,articles);

    }

    private List<ArticleDTO> createArticles() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ArticleDTO> test = objectMapper.readValue(new File("src/test/resources/static/allProducts.json"), new TypeReference<>() {
        });
        return test;
    }


}
