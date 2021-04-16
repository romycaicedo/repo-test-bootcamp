package com.tourismAgency.service;

import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.DataNotFoundException;
import com.tourismAgency.exceptions.DestinationNotFoundException;
import com.tourismAgency.exceptions.FiltersException;
import com.tourismAgency.exceptions.InvalidDateException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface HotelService {

    List<HotelDTO> getHotels(Map<String,String> params) throws InvalidDateException, FiltersException, DataNotFoundException, DestinationNotFoundException;
}
