package store.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    public TicketDTO ticket;
    public StatusDTO status;
}
