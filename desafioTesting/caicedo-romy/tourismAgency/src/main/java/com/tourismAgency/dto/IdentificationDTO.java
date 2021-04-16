package com.tourismAgency.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationDTO {
    public String dni;
    public String name;
    public String lastName;
    public String birthDate;
    public String mail;

}
