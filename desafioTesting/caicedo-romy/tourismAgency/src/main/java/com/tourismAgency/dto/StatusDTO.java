package com.tourismAgency.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    public double code;
    public String message;
}
