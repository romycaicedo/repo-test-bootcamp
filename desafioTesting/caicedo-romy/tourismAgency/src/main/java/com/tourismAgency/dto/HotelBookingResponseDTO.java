package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class HotelBookingResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double interest;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public double total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public BookingHotelDTO bookingHotelDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StatusDTO status;
}
