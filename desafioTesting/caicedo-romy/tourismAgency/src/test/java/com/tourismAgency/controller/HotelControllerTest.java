package com.tourismAgency.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.dto.HotelBookingResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.HotelRepositoryImpl;
import com.tourismAgency.repository.UserRepositoryImpl;
import com.tourismAgency.service.HotelService;
import com.tourismAgency.service.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HotelControllerTest {

    HotelController hotelController;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private HotelService hotelService;

    @BeforeEach
    public void setup(){
        openMocks(this);
        hotelService = new HotelServiceImpl(new HotelRepositoryImpl(), new UserRepositoryImpl());
    }

    @Test
    @DisplayName("Get All Hotels.")
    void getAll() throws Exception{
        Map<String,String> params = new HashMap<>();
        List<HotelDTO> hotels = objectMapper.readValue(
                new File("src/main/resources/dbHotels.json"),
                new TypeReference<>() {
                });

        hotelController = new HotelController(hotelService);

        Assertions.assertEquals(hotels,hotelController.getHotels(params).getBody());

    }

    @Test
    @DisplayName("Get Hotels Filters.")
    void getByFilters() throws Exception{
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("destination","Puerto Iguazu");
        List<HotelDTO> hotels = objectMapper.readValue(
                new File("src/test/resources/dbHotelsFiltered.json"),
                new TypeReference<>() {
                });

        hotelController = new HotelController(hotelService);

        Assertions.assertEquals(hotels,hotelController.getHotels(params).getBody());

    }

    @Test
    @DisplayName("Successfull booking request")
    void booking() throws Exception{
        HotelBookingRequestDTO bookingRequest = objectMapper.readValue(
                new File("src/test/resources/dbBookingRq.json"),
                new TypeReference<>() {
                });
        HotelBookingResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbBooking.json"),
                new TypeReference<>() {
                });

        hotelController = new HotelController(hotelService);

        Assertions.assertEquals(bookingResponse,hotelController.booking(bookingRequest).getBody());

    }

    @Test
    @DisplayName("Handle Unknown Exception")
    void handleUnknownException(){
        hotelController= new HotelController();
        int status = hotelController.handleUnknownException().getStatusCodeValue();
        Assertions.assertEquals(500,status);
    }

    @Test
    @DisplayName("Invalid Dates Exception")
    void invalidDates(){
        hotelController= new HotelController();
        int status = hotelController.invalidDates(new InvalidDateException("Invalid Dates")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

    @Test
    @DisplayName("Invalid Filters")
    void badFilters(){
        hotelController= new HotelController();
        int status = hotelController.badFilters(new FiltersException("Search parameters used did not give result, please try again")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

    @Test
    @DisplayName("Data not found")
    void dataNotFound(){
        hotelController= new HotelController();
        int status = hotelController.dataNotFound(new DataNotFoundException("We are having problems to consult the available hotels, please try again later or contact the application admin")).getStatusCodeValue();
        Assertions.assertEquals(500,status);
    }

    @Test
    @DisplayName("Invalid Destination Exception")
    void invalidDestination(){
        hotelController= new HotelController();
        int status = hotelController.invalidDestination(new DestinationNotFoundException("Invalid destination")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Dues Exception")
    void invalidDues(){
        hotelController= new HotelController();
        int status = hotelController.invalidDues(new DuesException("Invalid Dues")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Hotel Exception")
    void invalidHotel(){
        hotelController= new HotelController();
        int status = hotelController.invalidHotel(new HotelNotFoundException("Hotel not Found")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid Email Exception")
    void invalidEmail(){
        hotelController= new HotelController();
        int status = hotelController.invalidEmail(new InvalidEmailException("Invalid Email")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid People Exception")
    void invalidPeople(){
        hotelController= new HotelController();
        int status = hotelController.invalidPeople(new PeopleAmountException("People Amount Exceeded")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }
    @Test
    @DisplayName("Invalid userName Exception")
    void invalidUserName(){
        hotelController= new HotelController();
        int status = hotelController.invalidUser(new UserNotFoundException("User not found")).getStatusCodeValue();
        Assertions.assertEquals(400,status);
    }

}
