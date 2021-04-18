package com.tourismAgency.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.dto.HotelBookingResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.HotelRepository;
import com.tourismAgency.repository.HotelRepositoryImpl;
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


import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


 class HotelServiceImplTest {

    private static List<HotelDTO> hotels;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static HotelBookingRequestDTO bookingRequest;


    HotelServiceImpl hotelService;

    @Mock
    private HotelRepository hotelRepository;


    @BeforeEach
    public void setup(){
        openMocks(this);
        hotelService = new HotelServiceImpl(new HotelRepositoryImpl(), new UserRepositoryImpl());
    }

    @Test
    @DisplayName("When no parameters, get all hotels.")
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



    @Test
    @DisplayName("When dates and destination in params, return hotels filtered.")
    void getByFilters() throws Exception {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("destination","Puerto Iguazu");
        hotels = objectMapper.readValue(new File("src/test/resources/dbHotelsFiltered.json"),
                new TypeReference<>() {
                });
        when(hotelRepository.getAll()).thenReturn(hotels);
        List<HotelDTO> responseHotels = hotelService.getHotels(params);
        //assert equals
        Assertions.assertEquals(responseHotels, hotels);
    }

    @Test
    @DisplayName("When booking json received.")
    void booking() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingRq.json"),
                new TypeReference<>() {
                });
        HotelBookingResponseDTO bookingResponse = objectMapper.readValue(new File("src/test/resources/dbBooking.json"),
                new TypeReference<>() {
                });
        HotelBookingResponseDTO responseBooking = hotelService.booking(bookingRequest);


        Assertions.assertEquals(responseBooking, bookingResponse);
    }

    @Test
    @DisplayName("No results found with filters, return exception")
    void wrongFilters() {
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/0001");
        params.put("dateTo","25/02/2021");
        params.put("destination","Bogota");

        Assertions.assertThrows(FiltersException.class,()->hotelService.getHotels(params));
    }
     @Test
     @DisplayName("Start Date must be less than end date, return exception")
     void wrongDatesOrder() {
         Map<String,String> params = new HashMap<>();
         params.put("dateFrom", "27/02/2021");
         params.put("dateTo","25/02/2021");
         params.put("destination","Bogota");

         Assertions.assertThrows(InvalidDateException.class,()->hotelService.getHotels(params));
     }

    @Test
    @DisplayName("No results found with filters, return exception")
    void wrongDestination(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "23/02/0001");
        params.put("dateTo","25/02/2021");
        params.put("destination","Bogotá");

        Assertions.assertThrows(DestinationNotFoundException.class,()->hotelService.getHotels(params));
    }


    @Test
    @DisplayName("Invalid date received to get hotels by filters, return exception.")
    void wrongDateGetByFilters(){
        Map<String,String> params = new HashMap<>();
        params.put("dateFrom", "43/02/2021");
        params.put("dateTo","25/02/2021");
        params.put("destination","Puerto Iguazu");

        Assertions.assertThrows(InvalidDateException.class,()->hotelService.getHotels(params));

    }

    @Test
    @DisplayName("When booking json received, with wrong email, return exception.")
    void bookingWrongEmail() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongMailRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(InvalidEmailException.class, ()-> hotelService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong hotel and room , return exception.")
    void bookingWrongHotelRooms() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongHotelRoomsRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(HotelNotFoundException.class, ()-> hotelService.booking(bookingRequest));
    }
    @Test
    @DisplayName("When booking json received, with wrong people amount, return exception.")
    void bookingWrongPeopleAmount() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongPeopleAmountRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(PeopleAmountException.class, ()-> hotelService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong hotel code, return exception.")
    void bookingHotelNotFound() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingWrongHotelRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(HotelNotFoundException.class, ()-> hotelService.booking(bookingRequest));
    }

    @Test
    @DisplayName("When booking json received, with wrong dates to make hotel reservation, return exception.")
    void bookingDatesNotAvailable() throws Exception{
        bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingDatesNotAvailableRq.json"),
                new TypeReference<>() {
                });

        Assertions.assertThrows(InvalidDateException.class, ()-> hotelService.booking(bookingRequest));
    }

     @Test
     @DisplayName("When booking json received, with wrong date format, return exception.")
     void bookingDatesWrong() throws Exception{
         bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingDatesWrongRq.json"),
                 new TypeReference<>() {
                 });

         Assertions.assertThrows(InvalidDateException.class, ()-> hotelService.booking(bookingRequest));
     }

     @Test
     @DisplayName("When booking json received, user not found, return exception.")
     void bookingUserNotFound() throws Exception{
         bookingRequest = objectMapper.readValue(new File("src/test/resources/dbBookingUserNotFoundRq.json"),
                 new TypeReference<>() {
                 });

         Assertions.assertThrows(UserNotFoundException.class, ()-> hotelService.booking(bookingRequest));
     }


}
