package com.tourismAgency.controller;

import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.dto.StatusDTO;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.service.HotelService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/api/v1/hotels")
    public ResponseEntity getHotels(@RequestParam Map<String,String> params) throws InvalidDateException, DataNotFoundException, FiltersException, DestinationNotFoundException {
        return new ResponseEntity(hotelService.getHotels(params), HttpStatus.OK);
    }

    @PostMapping("api/v1/booking")
    public ResponseEntity booking(@RequestBody HotelBookingRequestDTO hotelBookingRequestDTO) throws InvalidDateException, DestinationNotFoundException, InvalidEmailException, UserNotFoundException, PeopleAmountException, HotelNotFoundException, DuesException {
        return new ResponseEntity(hotelService.booking(hotelBookingRequestDTO),HttpStatus.OK);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<StatusDTO> handleUnknownException(Exception e) {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage("Internal error, please contact API Spring Boot Challenge Admin");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity invalidDates(InvalidDateException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FiltersException.class)
    public ResponseEntity badFilters(FiltersException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity dataNotFound(DataNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity invalidDestination(DestinationNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DuesException.class)
    public ResponseEntity invalidDues(DuesException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity invalidHotel(HotelNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity invalidEmail(InvalidEmailException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PeopleAmountException.class)
    public ResponseEntity invalidPeople(PeopleAmountException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity invalidUser(UserNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(500);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
