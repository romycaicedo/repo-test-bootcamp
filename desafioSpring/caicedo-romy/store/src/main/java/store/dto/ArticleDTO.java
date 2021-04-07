package store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
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




}
