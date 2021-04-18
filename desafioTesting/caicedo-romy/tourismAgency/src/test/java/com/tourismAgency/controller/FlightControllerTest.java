package com.tourismAgency.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.*;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.FlightRepositoryImpl;
import com.tourismAgency.repository.UserRepositoryImpl;
import com.tourismAgency.service.FlightService;
import com.tourismAgency.service.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.MockitoAnnotations.openMocks;

public class FlightControllerTest {

    FlightController flightController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private FlightService flightService;

    @BeforeEach
    void setup(){
        openMocks(this);
        flightService = new FlightServiceImpl(new FlightRepositoryImpl(), new UserRepositoryImpl());
    }

    @Test
    @DisplayName("Get All Hotels.")
    void getAll() throws Exception{
        Map<String,String> params = new HashMap<>();
        List<FlightDTO> flightDTOS = objectMapper.readValue(
                new File("src/main/resources/dbFlights.json"),
                new TypeReference<>() {
                });

        flightController = new FlightController(flightService);

        Assertions.assertEquals(flightDTOS,flightController.getFlights(params).getBody());

    }
    @Test
    @DisplayName("Get Hotels Filters.")
    void getByFilters() throws Exception{
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "10/02/2021");
        params.put("dateTo","21/02/2021");
        params.put("origin","Puerto Iguazu");
        params.put("destination","Bogota");
        List<FlightDTO> flight = objectMapper.readValue(
                new File("src/test/resources/dbFlightsFiltered.json"),
                new TypeReference<>() {
                });

        flightController = new FlightController(flightService);

        Assertions.assertEquals(flight,flightController.getFlights(params).getBody());

    }
    @Test
    @DisplayName("Get Hotels Filters, exception.")
    void getByFiltersSameDestination(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "10/02/2021");
        params.put("dateTo","21/02/2021");
        params.put("origin","Bogota");
        params.put("destination","Bogota");

        flightController = new FlightController(flightService);

        Assertions.assertThrows(FiltersException.class ,() -> flightController.getFlights(params).getBody());

    }

    @Test
    @DisplayName("Successfull booking request")
    void booking() throws Exception{
        FlightReservationRequestDTO bookingRequest = objectMapper.readValue(
                new File("src/test/resources/dbFlightBookingRq.json"),
                new TypeReference<>() {
                });
        FlightReservationResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbFlightBookingRs.json"),
                new TypeReference<>() {
                });

        flightController = new FlightController(flightService);

        Assertions.assertEquals(bookingResponse,flightController.booking(bookingRequest).getBody());

    }
    @Test
    @DisplayName("Handle Unknown Exception")
    void handleUnknownException(){
        flightController= new FlightController();
        int status = flightController.handleUnknownException().getStatusCodeValue();
        Assertions.assertEquals(500,status);
    }
    @Test
    @DisplayName("Invalid Dates Exception")
    void invalidDates(){
        flightController= new FlightController();
        int status = flightController.invalidDates(new InvalidDateException("Invalid Dates")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

    @Test
    @DisplayName("Invalid Filters")
    void badFilters(){
        flightController= new FlightController();
        int status = flightController.badFilters(new FiltersException("Search parameters used did not give result, please try again")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

    @Test
    @DisplayName("Data not found")
    void dataNotFound(){
        flightController= new FlightController();
        int status = flightController.dataNotFound(new DataNotFoundException("We are having problems to consult the available hotels, please try again later or contact the application admin")).getStatusCodeValue();
        Assertions.assertEquals(500,status);
    }

    @Test
    @DisplayName("Invalid Destination Exception")
    void invalidDestination(){
        flightController= new FlightController();
        int status = flightController.invalidDestination(new DestinationNotFoundException("Invalid destination")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Dues Exception")
    void invalidDues(){
        flightController= new FlightController();
        int status = flightController.invalidDues(new DuesException("Invalid Dues")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Flight Exception")
    void invalidHotel(){
        flightController= new FlightController();
        int status = flightController.flightNotFound(new FlightNotFoundException("Flight not Found")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Email Exception")
    void invalidEmail(){
        flightController= new FlightController();
        int status = flightController.invalidEmail(new InvalidEmailException("Invalid Email")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid People Exception")
    void invalidPeople(){
        flightController= new FlightController();
        int status = flightController.invalidPeople(new PeopleAmountException("Seats Amount incorrect")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid userName Exception")
    void invalidUserName(){
        flightController= new FlightController();
        int status = flightController.invalidUser(new UserNotFoundException("User not found")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

}
