package com.tourismAgency.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    public String type;
    public String number;
    public int dues;

}
