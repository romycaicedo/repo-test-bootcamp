package com.tourismAgency.repository;

import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.DestinationNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface HotelRepository {
    List<HotelDTO> getAll() ;
    List<HotelDTO> getByFilters(Map<String,String> params) throws DestinationNotFoundException;
    boolean validateHotel(String hotelCode);
    boolean validateRooms(String hotelCode, String rooms);
    boolean validateDestination(String hotelCode,String destination);
    long getNightPrice(String hotelCode);
    boolean validateAvailavility(String hotelCode, String dateFrom, String dateTo);
}
