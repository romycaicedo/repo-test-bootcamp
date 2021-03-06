package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
public class BookingFlightDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateFrom;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String origin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String destination;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String flightNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int seats;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String seatType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<UserDTO> people;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PaymentDTO paymentMethod;
}
