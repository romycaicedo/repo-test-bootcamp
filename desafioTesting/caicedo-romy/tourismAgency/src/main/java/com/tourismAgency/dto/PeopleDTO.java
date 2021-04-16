package com.tourismAgency.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {
        public List<IdentificationDTO> people;
}
