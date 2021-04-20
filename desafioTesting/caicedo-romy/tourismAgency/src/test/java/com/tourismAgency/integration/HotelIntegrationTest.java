package com.tourismAgency.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.controller.HotelController;
import com.tourismAgency.dto.FlightReservationResponseDTO;
import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.dto.HotelBookingResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.repository.HotelRepositoryImpl;
import com.tourismAgency.repository.UserRepositoryImpl;
import com.tourismAgency.service.HotelService;
import com.tourismAgency.service.HotelServiceImpl;
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
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(HotelController.class)
public class HotelIntegrationTest {




        private static List<HotelDTO> hotels;
        private static HotelBookingRequestDTO bookingRequest;
        private static final ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        private MockMvc mockMvc;


        @MockBean
        private HotelService hotelService;

        public  void setUp() throws IOException {
            initMocks(this);
            hotelService = new HotelServiceImpl(new HotelRepositoryImpl(), new UserRepositoryImpl());

        }
        @Test
        @DisplayName("Should get all hotels")
        void shouldGetAllHotels() throws Exception {
            //mock service
            Map<String,String> params = new HashMap<>();
            hotels = objectMapper.readValue(new File("src/main/resources/dbHotels.json"),
                    new TypeReference<>() {
                    });
            when(hotelService.getHotels(params)).thenReturn(hotels);

            //get /articles
            //map into objects
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels"))
                    .andExpect(status().isOk()).andReturn();

            List<HotelDTO> responseHotels =
                    objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                    });
            //assert equals
            Assertions.assertEquals(responseHotels, hotels);
        }
        @Test
        @DisplayName("Should Hotels by filter")
        void shouldGetHotelsByFilter() throws Exception {
            //mock service
            Map<String,String> params = new HashMap<>();
            params.put("dateFrom","23/02/2021");
            params.put("dateTo","25/02/2021");
            params.put("destination","Bogotá");
            hotels = objectMapper.readValue(new File("src/test/resources/dbHotelsFiltered.json"),
                    new TypeReference<>() {
                    });
            when(hotelService.getHotels(params)).thenReturn(hotels);

            //get /articles
            //map into objects
            MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels?dateFrom=23/02/2021&dateTo=25/02/2021&destination=Bogotá").accept(MediaType.ALL))
                    .andExpect(status().isOk()).andReturn();


            List<HotelDTO> responseHotels =
                    objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                    });
            //assert equals
            Assertions.assertEquals(responseHotels, hotels);
        }

    @Test
    @DisplayName("Should get a booking successfully")
    void getBooking() throws Exception {
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingRq.json"),
                new TypeReference<>() {
                });
        HotelBookingResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbBooking.json"),
                new TypeReference<>() {
                });

        when(hotelService.booking(bookingRequest)).thenReturn(bookingResponse);
        final String jsonContent = objectMapper.writeValueAsString(bookingRequest);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/booking").contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.ALL))
                .andExpect(status().isOk()).andReturn();

        HotelBookingResponseDTO mvcResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(bookingResponse, mvcResponse);
    }


    }

