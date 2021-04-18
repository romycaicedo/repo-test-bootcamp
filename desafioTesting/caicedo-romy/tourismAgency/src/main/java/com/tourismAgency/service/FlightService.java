package com.tourismAgency.service;

import com.tourismAgency.dto.FlightDTO;
import com.tourismAgency.dto.FlightReservationRequestDTO;
import com.tourismAgency.dto.FlightReservationResponseDTO;
import com.tourismAgency.exceptions.*;

import java.util.List;
import java.util.Map;

public interface FlightService {

    List<FlightDTO> getAll(Map<String, String> params) throws InvalidDateException, DestinationNotFoundException, FiltersException, DataNotFoundException;
    FlightReservationResponseDTO booking(FlightReservationRequestDTO booking) throws UserNotFoundException, InvalidDateException, FlightNotFoundException, PeopleAmountException, DestinationNotFoundException, InvalidEmailException, DuesException;
}
