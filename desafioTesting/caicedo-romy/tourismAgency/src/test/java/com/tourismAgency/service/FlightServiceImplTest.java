package com.tourismAgency.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.*;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.FlightRepository;
import com.tourismAgency.repository.FlightRepositoryImpl;
import com.tourismAgency.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class FlightServiceImplTest {

    private List<FlightDTO> flights;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static FlightReservationRequestDTO bookingRequest;

    FlightServiceImpl flightService;

    @Mock
    FlightRepository flightRepository;

    @BeforeEach
    public void setup(){
        openMocks(this);
        flightService = new FlightServiceImpl(new FlightRepositoryImpl(), new UserRepositoryImpl());
    }

    @Test
    @DisplayName("When no parameters, get all flights.")
    void getAllHotels() throws Exception {
        Map<String,String> params = new HashMap<>();
        flights = objectMapper.readValue(new File("src/main/resources/dbFlights.json"),
                new TypeReference<>() {
                });
        when(flightRepository.getAll()).thenReturn(flights);
        List<FlightDTO> responseFlights = flightService.getAll(params);
        //assert equals
        Assertions.assertEquals(responseFlights, flights);


    }

    @Test
    @DisplayName("When dates , origin and destination in params, return flights filtered.")
    void getByFilters() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "10/02/2021");
        params.put("dateTo","21/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogota");
        flights = objectMapper.readValue(new File("src/test/resources/dbFlightsFiltered.json"),
                new TypeReference<>() {
                });
        when(flightRepository.getAll()).thenReturn(flights);
        List<FlightDTO> responseFlights = flightService.getAll(params);
        //assert equals
        Assertions.assertEquals(responseFlights, flights);
    }

    @Test
    @DisplayName("When booking receive, return success.")
    void booking() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbFlightBookingRq.json"),
                new TypeReference<>() {
                });
        FlightReservationResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbFlightBookingRs.json"),
                new TypeReference<>() {
                });
        FlightReservationResponseDTO responseBooking = flightService.booking(bookingRequest);


        Assertions.assertEquals(responseBooking, bookingResponse);
    }

    @Test
    @DisplayName("No results found with filters, return exception")
    void wrongFilters() {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/0001");
        params.put("dateTo","25/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogota");

        Assertions.assertThrows(FiltersException.class,()->flightService.getAll(params));
    }
    @Test
    @DisplayName("Start Date must be less than end date, return exception")
    void wrongDatesOrder() {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "27/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogota");

        Assertions.assertThrows(InvalidDateException.class,()->flightService.getAll(params));
    }

    @Test
    @DisplayName("No results found with filters, return exception")
    void wrongDestination(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/0001");
        params.put("dateTo","25/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogotá");

        Assertions.assertThrows(DestinationNotFoundException.class,()->flightService.getAll(params));
    }

    @Test
    @DisplayName("No results found with filters, return exception")
    void wrongOrigin(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/0001");
        params.put("dateTo","25/02/2021");
        params.put("origin","Puerto Iguazú");
        params.put("destination","Bogota");

        Assertions.assertThrows(DestinationNotFoundException.class,()->flightService.getAll(params));
    }
    @Test
    @DisplayName("Invalid date received to get hotels by filters, return exception.")
    void wrongDateGetByFilters(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "43/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogota");

        Assertions.assertThrows(InvalidDateException.class,()->flightService.getAll(params));

    }
    @Test
    @DisplayName("When booking json received, with wrong email, return exception.")
    void bookingWrongEmail() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingFlightWrongMailRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(InvalidEmailException.class, ()-> flightService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong people amount, return exception.")
    void bookingWrongPeopleAmount() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongSeatsRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(PeopleAmountException.class, ()-> flightService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong flight code, return exception.")
    void bookingFlightNotFound() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongFlightRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(FlightNotFoundException.class, ()-> flightService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong dates to make flight reservation, return exception.")
    void bookingDatesNotAvailable() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingDatesFlightNotAvailableRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(InvalidDateException.class, ()-> flightService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong date format, return exception.")
    void bookingDatesWrong() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingFlightDatesWrongRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(InvalidDateException.class, ()-> flightService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, user not found, return exception.")
    void bookingUserNotFound() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingFlightUserNotFoundRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(UserNotFoundException.class, ()-> flightService.booking(bookingRequest));
    }
}
