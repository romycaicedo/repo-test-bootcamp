package store.respository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import store.comparators.AscendingComparator;
import store.comparators.AscendingPriceComparator;
import store.comparators.DescendingComparator;
import store.comparators.DescendingPriceComparator;
import store.dto.ArticleDTO;
import store.exceptions.ArticleNotFoundException;
import store.exceptions.StockOutOfBoundsException;

import java.io.File;
import java.io.FileNotFoundException;
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
    @Override
    public List<ArticleDTO> getByCategory(String category) {
        List<ArticleDTO> articleDTOS = loadDataBase();
        return articleDTOS.stream().filter(articleDTO -> matchWithCategory(category,articleDTO)).collect(Collectors.toList());

    }
        @Override
        public ArticleDTO getById(int id) throws ArticleNotFoundException {
        List<ArticleDTO> articleDTOS = loadDataBase();
        ArticleDTO dto = new ArticleDTO();
        for(int i =0; i<articleDTOS.size();i++){
            if(articleDTOS.get(i).getProductId().equals(id)){
                dto = articleDTOS.get(i);
            }
        }
        if(dto.getProductId()==null){
            throw new ArticleNotFoundException("El Articulo con el id "+ id+ " no existe" );
        }

        return dto;

    }
    @Override
    public List<ArticleDTO> getByFilters(Map<String,String> params) {

        List<ArticleDTO> articleDTOS = loadDataBase();
        List<ArticleDTO> total = new ArrayList<>();
        List<List<ArticleDTO>> lists1 = new ArrayList<>();
        List<ArticleDTO> result = new ArrayList<>();
        if (params.containsKey("category")) {
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithCategory(params.get("category"), articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("brand")) {
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithBrand(params.get("brand"), articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("minPrice") && params.containsKey("maxPrice")) {

            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithPriceRange(Double.parseDouble(params.get("minPrice").replace(".", "")), Double.parseDouble(params.get("maxPrice").replace(".", "")), articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("freeShipping")) {
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithFreeShipping(Boolean.parseBoolean(params.get("freeShipping")), articleDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("prestige")) {
            lists1.add(articleDTOS.stream().filter(articleDTO -> matchWithPrestige(params.get("prestige"), articleDTO)).collect(Collectors.toList()));
        }


        for (List<ArticleDTO> arr : lists1) {
            for (ArticleDTO dto : arr) {
                result.add(dto);
            }
        }

        total = result.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(articleDTOLongEntry -> articleDTOLongEntry.getValue() > lists1.size() - 1).map(articleDTOLongEntry -> articleDTOLongEntry.getKey()).collect(Collectors.toList());
        if (params.containsKey("order"))
            if (params.get("order").equals("0"))
                total.sort(new AscendingComparator());
            else if (params.get("order").equals("1")) {
                total.sort(new DescendingComparator());
            } else if (params.get("order").equals("2")) {
                total.sort(new AscendingPriceComparator());
            } else if (params.get("order").equals("3")) {
                total.sort(new DescendingPriceComparator());
            }


        return total;
    }



    public boolean matchById(int id, ArticleDTO articleDTO){
        boolean is = articleDTO.getProductId().equals(id);
        return is;
    }


// Metodo que calcula el total de una solicitud de compra
    public double totalPurchase (List<ArticleDTO> articleDTOList) throws ArticleNotFoundException {
        double price = 0;
        List<ArticleDTO> articleDTOS = loadDataBase();
        List<ArticleDTO> precios = new ArrayList<>();
        Map<Double,Double> valores = new HashMap<>();
        for(int i = 0; i<articleDTOList.size();i++) {
             precios.add(getById(articleDTOList.get(i).getProductId()));
        }
        for(ArticleDTO dtoList : articleDTOList){
            for (ArticleDTO dto : precios){
                valores.put(removeCharacters(dto.getPrice()),dtoList.getQuantity());
            }
        }

        for(Map.Entry<Double,Double> entry :valores.entrySet()){
            price = price + entry.getKey()* entry.getValue();
        }
        return  price;
    }



//Metodo para remover caracteres del precio
    private double removeCharacters(String precio){
        Double precioFinal = Double.parseDouble(precio.replace("$", "").replace(".",""));
        return precioFinal;
    }
//Metodos para consultas especificas
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
        if(removeCharacters(articleDTO.getPrice()) >= query1 && removeCharacters(articleDTO.getPrice())<=query2)
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
//Metodo que lee el JSON
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
//Metodo que modifica el JSON
    @Override
    public void modifyStock(ArticleDTO articles) throws FileNotFoundException, ArticleNotFoundException, StockOutOfBoundsException {
        List<ArticleDTO> db = loadDataBase();

            for(int j= 0; j<db.size();j++) {
                if (getById(articles.getProductId()) != null) {
                    if (db.get(j).getProductId() == articles.getProductId())
                        if(db.get(j).getQuantity() != articles.getQuantity()){
                            if(db.get(j).getQuantity()>articles.getQuantity()){
                                db.get(j).setQuantity(articles.getQuantity());
                            }
                            else
                                throw new StockOutOfBoundsException("El stock no es suficiente");
                        }

                }
            }


        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:dbProductos.json");
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(articles!=null)
                objectMapper.writeValue(file, db);
           // return true;
        }catch (Exception e){
            e.printStackTrace();
           //return false;
        }
    }
}
