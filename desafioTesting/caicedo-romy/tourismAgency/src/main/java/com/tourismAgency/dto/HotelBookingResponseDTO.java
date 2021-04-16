package com.tourismAgency.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelBookingResponseDTO {
    public String userName;
    public double amount;
    public double interest;
    public double total;
    public BookingHotelDTO bookingHotelDTO;
    public StatusDTO status;
}
