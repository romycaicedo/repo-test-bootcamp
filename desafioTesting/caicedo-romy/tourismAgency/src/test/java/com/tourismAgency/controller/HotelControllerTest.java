package com.tourismAgency.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.InvalidDateException;
import com.tourismAgency.service.HotelService;
import com.tourismAgency.service.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Value("${app.hotel.path}")
    String hotelPath;
    private static List<HotelDTO> hotels;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private HotelService hotelService;

    public  void setUp() throws IOException {
    initMocks(this);
    hotelService = new HotelServiceImpl();

    }
    @Test
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
    void shouldGetHotelsByFilter() throws Exception {
        //mock service
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom","23/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("destination","Bogotá");
        hotels = objectMapper.readValue(new File("src/test/db_getByFilterHotels.json"),
                new TypeReference<>() {
                });
        when(hotelService.getHotels(params)).thenReturn(hotels);

        //get /articles
        //map into objects
        MvcResult  mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels?dateFrom=23/02/2021&dateTo=25/02/2021&destination=Bogotá").accept(MediaType.ALL))
                .andExpect(status().isOk()).andReturn();


        List<HotelDTO> responseHotels =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });
        //assert equals
        Assertions.assertEquals(responseHotels, hotels);
    }


}
