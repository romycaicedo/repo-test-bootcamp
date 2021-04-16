package com.tourismAgency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingHotelDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateFrom;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String destination;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String hotelCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int peopleAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String roomType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PeopleDTO people;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PaymentDTO paymentMethod;
}
