package com.tourismAgency.repository;

import com.tourismAgency.dto.FlightDTO;
import com.tourismAgency.exceptions.DestinationNotFoundException;
import com.tourismAgency.exceptions.FiltersException;

import java.util.List;
import java.util.Map;

public interface FlightRepository {

     List<FlightDTO> getAll();
     boolean matchWithSeatType(String seatType, String flightNumber);
     boolean matchWithFlightNumber(String flightNumber);
     boolean validateAvailability(String flightNumber, String dateFrom, String dateTo);
     long getPrice(String hotelCode);
     boolean validateDestination(String hotelCode,String destination, String origin);
     List<FlightDTO> getByFilters(Map<String,String> params) throws DestinationNotFoundException, FiltersException;
}
