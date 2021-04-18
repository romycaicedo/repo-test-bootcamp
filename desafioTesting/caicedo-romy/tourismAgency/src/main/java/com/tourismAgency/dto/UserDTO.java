package com.tourismAgency.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    public String dni;
    public String name;
    public String lastName;
    public String birthDate;
    public String mail;

}
