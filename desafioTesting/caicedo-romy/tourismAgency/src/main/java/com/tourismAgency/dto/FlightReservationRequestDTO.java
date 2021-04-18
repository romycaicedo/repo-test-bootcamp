package com.tourismAgency.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class FlightReservationRequestDTO {
    public String userName;
    public BookingFlightDTO flightReservation;
}
