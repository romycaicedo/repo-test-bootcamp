package com.tourismAgency.integration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.controller.FlightController;
import com.tourismAgency.dto.FlightDTO;
import com.tourismAgency.dto.FlightReservationRequestDTO;
import com.tourismAgency.dto.FlightReservationResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.FlightRepositoryImpl;
import com.tourismAgency.repository.UserRepositoryImpl;
import com.tourismAgency.service.FlightService;
import com.tourismAgency.service.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
public class FlightIntegrationTest {


    private static List<FlightDTO> flights;
    private static FlightReservationRequestDTO bookingRequest;
    private static final ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        private MockMvc mockMvc;


        @MockBean
        private FlightService flightService;

        public  void setUp() throws IOException {
            openMocks(this);
            flightService = new FlightServiceImpl(new FlightRepositoryImpl(), new UserRepositoryImpl());

        }
        @Test
        @DisplayName("Get All flights")
        void shouldGetAllFlights() throws Exception {
            //mock service
            Map<String,String> params = new HashMap<>();
            flights = objectMapper.readValue(new File("src/main/resources/dbFlights.json"),
                    new TypeReference<>() {
                    });
            when(flightService.getAll(params)).thenReturn(flights);

            //get /articles
            //map into objects
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                    .andExpect(status().isOk()).andReturn();

            List<FlightDTO> responseFlights =
                    objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                    });
            //assert equals
            Assertions.assertEquals(responseFlights, flights);
        }
        @Test
        @DisplayName("Get Flights with filter")
        void shouldGetFlightsByFilter() throws Exception {
            //mock service
            Map<String,String> params = new HashMap<>();
            params.put("dateFrom","10/02/2021");
            params.put("dateTo","21/02/2021");
            params.put("origin","Puerto Iguazu");
            params.put("destination","Bogota");
            flights = objectMapper.readValue(new File("src/test/resources/dbFlightsFiltered.json"),
                    new TypeReference<>() {
                    });
            when(flightService.getAll(params)).thenReturn(flights);

            //get /articles
            //map into objects
            MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights?dateFrom=10/02/2021&dateTo=21/02/2021&origin=Puerto Iguazu&destination=Bogota").accept(MediaType.ALL))
                    .andExpect(status().isOk()).andReturn();


            List<FlightDTO> responseFlights =
                    objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                    });
            //assert equals
            Assertions.assertEquals(responseFlights, flights);
        }

        @Test
        @DisplayName("Should get a booking successfully")
        void getBooking() throws Exception {
            bookingRequest = objectMapper.readValue(new File("src/test/resources/dbFlightBookingRq.json"),
                    new TypeReference<>() {
                    });
            FlightReservationResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbFlightBookingRs.json"),
                    new TypeReference<>() {
                    });

            when(flightService.booking(bookingRequest)).thenReturn(bookingResponse);
            final String jsonContent = objectMapper.writeValueAsString(bookingRequest);

            MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flight-reservation").contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.ALL))
                    .andExpect(status().isOk()).andReturn();

            FlightReservationResponseDTO mvcResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

            Assertions.assertEquals(bookingResponse, mvcResponse);
        }


    }

