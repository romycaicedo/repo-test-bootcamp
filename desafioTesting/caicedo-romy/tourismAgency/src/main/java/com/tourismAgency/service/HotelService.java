package com.tourismAgency.service;

import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.dto.HotelBookingResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelDTO> getHotels(Map<String,String> params) throws InvalidDateException, FiltersException, DataNotFoundException, DestinationNotFoundException;
    HotelBookingResponseDTO booking(HotelBookingRequestDTO booking) throws UserNotFoundException, InvalidDateException, HotelNotFoundException, PeopleAmountException, DestinationNotFoundException, InvalidEmailException, DuesException;
}
