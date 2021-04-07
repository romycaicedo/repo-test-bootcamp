package store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO implements Comparable<ArticleDTO> {
    @JsonProperty("Producto")
    public Integer productId;
    @JsonProperty("Nombre")
    public String name;
    @JsonProperty("Categoria")
    public String category;
    @JsonProperty("Marca")
    public String brand;
    @JsonProperty("Precio")
    public String price;
    @JsonProperty("Cantidad")
    public double quantity;
    @JsonProperty("Envio Gratis")
    public String freeShipping;
    @JsonProperty("Prestigio")
    public String prestige;


    public enum SortParameter {
        NAME_SORT, PRICE_SORT
    }


    @Override
        int comparison;
        for (SortParameter parameter : parameters) {
            switch (parameter) {
                case NAME_SORT:
                    comparison = getName().compareTo(o.getName());
                    if (comparison != 0) return comparison;
                    break;
                case PRICE_SORT:
                    comparison = getPrice().compareTo(o.getPrice());
                    if (comparison != 0) return comparison;
                    break;

            }

        }
        return 0;
    }

}
