package store.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    public List<PurchaseDTO> purchase;
    public double total;
}
