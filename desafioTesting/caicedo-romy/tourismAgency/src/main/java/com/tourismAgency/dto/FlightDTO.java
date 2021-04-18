package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FlightDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String flightNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String origin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String destination;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String seatType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateFrom;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateTo;

}
