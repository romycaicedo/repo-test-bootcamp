package store.respository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import store.dto.ArticleDTO;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



@Repository
public class ArticleRepositoryImpl implements ArticleRepository{

    @Override
    public List<ArticleDTO> getAll(){
        List<ArticleDTO> articleDTOS = loadDataBase();
        return articleDTOS;
    }
    public List<ArticleDTO> getByCategory(String category) {
        List<ArticleDTO> articleDTOS = loadDataBase();
        return articleDTOS.stream().filter(articleDTO -> matchWithCategory(category,articleDTO)).collect(Collectors.toList());

    }
    public List<ArticleDTO> getByFilters(Map<String,String> params) {

        List<ArticleDTO> articleDTOS = loadDataBase();
        List<ArticleDTO> total = new ArrayList<>();
        List<List<ArticleDTO>> lists1 = new ArrayList<>();
        List<ArticleDTO> result = new ArrayList<>();
        if(params.containsKey("category")){
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithCategory(params.get("category"),articleDTO)).collect(Collectors.toList()));
        }
        if(params.containsKey("brand")){
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithBrand(params.get("brand"),articleDTO)).collect(Collectors.toList()));
        }
        if(params.containsKey("minPrice") && params.containsKey("maxPrice")){

            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithPriceRange(Double.parseDouble(params.get("minPrice")),Double.parseDouble(params.get("maxPrice")),articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("freeShipping")){
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithFreeShipping(Boolean.parseBoolean(params.get("freeShipping")),articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("prestige")){
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithPrestige(params.get("prestige"),articleDTO)).collect(Collectors.toList()));
        }


            for(List<ArticleDTO> arr: lists1){
                for(ArticleDTO dto : arr){
                    result.add(dto);
                }
            }

        total = result.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(articleDTOLongEntry -> articleDTOLongEntry.getValue()>lists1.size()-1).map(articleDTOLongEntry -> articleDTOLongEntry.getKey()).collect(Collectors.toList());
        if(params.containsKey("order"))
            if(params.get("order").equals("0"))
             Collections.sort(total);

            else
                Collections.sort(total,Collections.reverseOrder());

        return total;
    }
    private double removeCharacters(String precio){
        Double precioFinal = Double.parseDouble(precio.replace("$", ""));
        return precioFinal;
    }

    private boolean matchWithCategory(String query, ArticleDTO articleDTO) {
        boolean is = articleDTO.getCategory().toUpperCase().contains(query.toUpperCase());
        return is;
    }
    private boolean matchWithBrand(String query, ArticleDTO articleDTO) {
        boolean is = articleDTO.getBrand().toUpperCase().contains(query.toUpperCase());
        return is;
    }
    private boolean matchWithPriceRange(double query1, double query2,ArticleDTO articleDTO) {
        boolean is ;
        if(removeCharacters(articleDTO.getPrice()) >query1 && removeCharacters(articleDTO.getPrice())<query2)
            is = true;
        else
            is = false;
        return is;
    }
    private boolean matchWithFreeShipping(boolean query, ArticleDTO articleDTO) {
        String value = query? "SI":"NO";
        boolean is = articleDTO.getFreeShipping().equals(value);
        return is;
    }
    private boolean matchWithPrestige(String prestige, ArticleDTO articleDTO) {
        boolean is = articleDTO.getPrestige().equals(prestige);
        return is;
    }






    private List<ArticleDTO> loadDataBase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:dbProductos.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ArticleDTO>> typeRef = new TypeReference<>() {};
        List<ArticleDTO> articleDTOS = null;

        try {
            articleDTOS = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return articleDTOS;
    }
}
