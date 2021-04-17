package com.tourismAgency.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.repository.HotelRepository;
import com.tourismAgency.repository.HotelRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HotelServiceImplTest {

    private static List<HotelDTO> hotels;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    HotelServiceImpl hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @BeforeEach
    public void setup(){
        initMocks(this);
        hotelService = new HotelServiceImpl(new HotelRepositoryImpl());
    }

    @Test
    void getAllHotels() throws Exception {
        Map<String,String> params = new HashMap<>();
        hotels = objectMapper.readValue(new File("src/main/resources/dbHotels.json"),
                new TypeReference<>() {
                });
        when(hotelRepository.getAll()).thenReturn(hotels);
        List<HotelDTO> responseHotels = hotelService.getHotels(params);
        //assert equals
        Assertions.assertEquals(responseHotels, hotels);


    }
}
