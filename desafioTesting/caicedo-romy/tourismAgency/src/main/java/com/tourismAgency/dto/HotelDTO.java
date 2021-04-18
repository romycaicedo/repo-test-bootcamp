package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import org.springframework.core.annotation.AliasFor;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class HotelDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String hotelCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String city;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String roomType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String nightPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String availableFrom;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String  availableTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean reserved;

}
