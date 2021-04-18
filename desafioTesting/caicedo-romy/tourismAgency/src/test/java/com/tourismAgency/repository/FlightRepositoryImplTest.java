package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.FlightDTO;
import com.tourismAgency.dto.HotelDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.MockitoAnnotations.openMocks;

public class FlightRepositoryImplTest {

    FlightRepository flightRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static  List<FlightDTO> flights;

    @BeforeEach
    public void setup(){
        openMocks(this);
        flightRepository = new FlightRepositoryImpl();
    }

    @Test
    @DisplayName("Get all hotels")
    void getAll() throws IOException {
        flights = objectMapper.readValue(new File("src/main/resources/dbFlights.json"),
                new TypeReference<>() {
                });
        List<FlightDTO> responseFlights = flightRepository.getAll();
        //assert equals
        Assertions.assertEquals(responseFlights, flights);
    }

    @Test
    @DisplayName("Validate Seat Type")
    void matchWithSeatType() {
        boolean isTrue = flightRepository.matchWithSeatType("BUSINESS", "PIBA-1420");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Validate FlightNumber")
    void matchWithFlightNumber() {
        boolean isTrue = flightRepository.matchWithFlightNumber("PIBA-1420");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }
    @Test
    @DisplayName("Validate Rooms")
    void validateAvailability() {
        boolean isTrue = flightRepository.validateAvailability("PIBA-1420","10/02/2021","20/02/2021");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Validate Destination")
    void validateDestination() {
        boolean isTrue = flightRepository.validateDestination("PIBA-1420","Bogota","Puerto Iguazu");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }
    @Test
    @DisplayName("Get Flight Price")
    void getPrice() {
        long price = flightRepository.getPrice("PIBA-1420");
        //assert equals
        Assertions.assertEquals(43200, price);
    }
    @Test
    @DisplayName("Get Hotels by filter, expected Hotels")
    void getByFilters() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "10/02/2021");
        params.put("dateTo","21/02/2021");
        params.put("origin", "Puerto Iguazu");
        params.put("destination","Bogota");

        flights = objectMapper.readValue(new File("src/test/resources/dbFlightsFiltered.json"),
                new TypeReference<>() {
                });
        List<FlightDTO> flightDTOS = flightRepository.getByFilters(params);

        //assert equals
        Assertions.assertEquals(flights, flightDTOS);
    }

}
