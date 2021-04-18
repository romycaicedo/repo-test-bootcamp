package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class FlightReservationResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double interest;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public BookingFlightDTO flightReservation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StatusDTO status;
}
