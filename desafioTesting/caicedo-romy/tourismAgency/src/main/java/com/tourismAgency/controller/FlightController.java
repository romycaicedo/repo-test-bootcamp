package com.tourismAgency.controller;

import com.tourismAgency.dto.FlightReservationRequestDTO;
import com.tourismAgency.dto.StatusDTO;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.service.FlightService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@NoArgsConstructor
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("/api/v1/flights")
    public ResponseEntity getFlights(@RequestParam Map<String,String> params) throws InvalidDateException, FiltersException, DestinationNotFoundException, DataNotFoundException {
        return new ResponseEntity(flightService.getAll(params), HttpStatus.OK);
    }

    @PostMapping("/api/v1/flight-reservation")
    public ResponseEntity booking(@RequestBody FlightReservationRequestDTO flightReservationRequestDTO) throws InvalidDateException, FlightNotFoundException, InvalidEmailException, UserNotFoundException, PeopleAmountException, DestinationNotFoundException, DuesException {
        return new ResponseEntity(flightService.booking(flightReservationRequestDTO),HttpStatus.OK);
    }


    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<StatusDTO> handleUnknownException() {
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
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity flightNotFound(FlightNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuesException.class)
    public ResponseEntity invalidDues(DuesException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity invalidEmail(InvalidEmailException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PeopleAmountException.class)
    public ResponseEntity invalidPeople(PeopleAmountException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity invalidUser(UserNotFoundException n)
    {
        StatusDTO error= new StatusDTO();
        error.setCode(400);
        error.setMessage(n.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }



}
