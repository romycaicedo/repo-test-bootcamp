package com.tourismAgency.service;

import com.tourismAgency.dto.HotelBookingResponseDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.dto.HotelBookingRequestDTO;
import com.tourismAgency.exceptions.DataNotFoundException;
import com.tourismAgency.exceptions.DestinationNotFoundException;
import com.tourismAgency.exceptions.FiltersException;
import com.tourismAgency.exceptions.InvalidDateException;
import com.tourismAgency.repository.HotelRepository;
import com.tourismAgency.utils.DateValidator;
import com.tourismAgency.utils.DateValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public List<HotelDTO> getHotels(Map<String, String> params) throws InvalidDateException, FiltersException, DataNotFoundException, DestinationNotFoundException {
        List<HotelDTO> hoteles = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateValidator validator = new DateValidatorImpl(formatter);
        if (params.size() == 0)
            hoteles = hotelRepository.getAll();
        else if (validator.isValid(params.get("dateFrom")) && validator.isValid(params.get("dateTo"))){
            validateDates(params.get("dateFrom"),params.get("dateTo"));
            hoteles = hotelRepository.getByFilters(params);
        }
        else
            throw new InvalidDateException("Date Format must be dd/mm/yyyy");
        if(hoteles.size()==0 && params.size()!=0){
            throw new FiltersException("Search parameters used did not give result, please try again");
        }
        if(hoteles.size()==0 && params.size() == 0){
            throw new DataNotFoundException("We are having problems to consult the available hotels, please try again later or contact the application admin");
        }
        return hoteles;
    }

    public HotelBookingResponseDTO booking(HotelBookingRequestDTO booking){
        
    }

    private LocalDate convertDate(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CharSequence seq = date;
        LocalDate localDate = LocalDate.parse(seq,formatter);
        return localDate;
    }

    private void  validateDates(String dateFrom, String dateTo) throws InvalidDateException {
        if (!convertDate(dateFrom).isBefore(convertDate(dateTo))) {
            throw new InvalidDateException("Start date must be less than the end date");

        }
    }

}
