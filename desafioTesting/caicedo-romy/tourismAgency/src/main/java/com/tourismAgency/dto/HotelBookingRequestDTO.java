package com.tourismAgency.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingRequestDTO {
    public String userName;
    public BookingHotelDTO booking;
}
