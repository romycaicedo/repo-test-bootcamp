package store.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArticleDTO {
    @JsonProperty("productId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAlias("Producto")
    public Integer productId;
    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAlias("Nombre")
    public String name;
    @JsonProperty("category")
    @JsonAlias("Categoria")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String category;
    @JsonProperty("brand")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAlias("Marca")
    public String brand;
    @JsonProperty("price")
    @JsonAlias("Precio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String price;
    @JsonProperty("quantity")
    @JsonAlias("Cantidad")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double quantity;
    @JsonAlias("Envio Gratis")
    @JsonProperty("free_shipping")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String freeShipping;
    @JsonProperty("prestige")
    @JsonAlias("Prestigio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String prestige;




}
