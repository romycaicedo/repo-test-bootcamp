package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

class HotelRepositoryImplTest {
    HotelRepository hotelRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static List<HotelDTO> hotels;

    @BeforeEach
    public void setup(){
        openMocks(this);
        hotelRepository = new HotelRepositoryImpl();
    }

    @Test
    @DisplayName("Get all hotels")
    void getAll() throws IOException {
        hotels = objectMapper.readValue(new File("src/main/resources/dbHotels.json"),
                new TypeReference<>() {
                });
        List<HotelDTO> responseHotels = hotelRepository.getAll();
        //assert equals
        Assertions.assertEquals(responseHotels, hotels);
    }

    @Test
    @DisplayName("Validate Hotel")
    void validateHotel() {
        boolean isTrue = hotelRepository.validateHotel("CH-0002");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Validate Availability")
    void validateAvailability() {
        boolean isTrue = hotelRepository.validateAvailavility("CH-0002","11/02/2021","20/02/2021");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Validate Rooms")
    void validateRooms() {
        boolean isTrue = hotelRepository.validateRooms("CH-0002","Double");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Validate Destination")
    void validateDestination() {
        boolean isTrue = hotelRepository.validateDestination("CH-0002","Puerto Iguazu");
        //assert equals
        Assertions.assertEquals(true, isTrue);
    }

    @Test
    @DisplayName("Get Night Price")
    void getNightPrice() {
        long price = hotelRepository.getNightPrice("CH-0002");
        //assert equals
        Assertions.assertEquals(6300, price);
    }

    @Test
    @DisplayName("Get Hotels by filter, expected Hotels")
    void getByFilters() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("destination","Puerto Iguazu");
        hotels = objectMapper.readValue(new File("src/test/resources/dbHotelsFiltered.json"),
                new TypeReference<>() {
                });
        List<HotelDTO> hotelDTO = hotelRepository.getByFilters(params);

        //assert equals
        Assertions.assertEquals(hotels, hotelDTO);
    }







}
