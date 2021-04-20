package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.DestinationNotFoundException;
import com.tourismAgency.exceptions.InvalidDateException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class HotelRepositoryImpl implements HotelRepository {

    // Method to get all hotels
    @Override
    public List<HotelDTO> getAll() {
        List<HotelDTO> all = loadData();
        return  all;
    }
// Validate if hotel exist
    @Override
    public boolean validateHotel(String hotelCode){
        boolean exists = false;
        List<HotelDTO> hotelDTOS = loadData();
        for(HotelDTO dto: hotelDTOS){
            if(dto.hotelCode.equals(hotelCode)) {
                exists = true;
            }
        }
        return exists;
    }
// Validate if the selected hotel is available is available for the dates selected
    @Override
    public boolean validateAvailavility(String hotelCode, String dateFrom, String dateTo){
        boolean isAvailable = false;
        List<HotelDTO> hotelDTOS = loadData();
        for(HotelDTO dto: hotelDTOS){
            if(dto.hotelCode.equals(hotelCode) && matchWithDates(dateFrom,dateTo,dto)) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }
// Validate if rooms are according to Hotel and rooms registered in repository
    @Override
    public boolean validateRooms(String hotelCode, String rooms){
        boolean match = false;
        List<HotelDTO> hotelDTOS = loadData();
        for(HotelDTO dto: hotelDTOS){
            if(dto.hotelCode.equals(hotelCode) && dto.roomType.equalsIgnoreCase(rooms)) {
                match = true;
            }
        }
        return match;
    }
// Validate if hotel city is according with destination selected
    @Override
    public boolean validateDestination(String hotelCode,String destination){
        boolean match = false;
        List<HotelDTO> hotelDTOS = loadData();
        for(HotelDTO dto: hotelDTOS){
            if(dto.hotelCode.equals(hotelCode) && dto.city.equals(destination)) {
                match = true;
            }
        }
        return match;
    }
// Get night price with hotel code
    @Override
    public long getNightPrice(String hotelCode){
        long price = 0;
        List<HotelDTO> hotelDTOS = loadData();
        for(HotelDTO dto: hotelDTOS){
            if(dto.hotelCode.equals(hotelCode) ) {
                price = Long.parseLong(dto.nightPrice);
            }
        }
        return price;
    }

// Method to get hotels by filters selected
    @Override
    public List<HotelDTO> getByFilters(Map<String,String> params) throws DestinationNotFoundException {
        List<HotelDTO> hotelDTOS = loadData();
        List<HotelDTO> total = new ArrayList<>();
        List<List<HotelDTO>> lists1 = new ArrayList<>();
        List<HotelDTO> result = new ArrayList<>();
        List<HotelDTO> validateDestination = new ArrayList<>();
        if (params.containsKey("dateFrom") && params.containsKey("dateTo")) {
                lists1.add(hotelDTOS.stream().filter(hotelDTO -> matchWithDates(params.get("dateFrom"),params.get("dateTo"),hotelDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("destination")) {
            validateDestination = getByDestination(params.get("destination"));
            if(validateDestination.size()== 0){
                throw new DestinationNotFoundException("The selected destination does not exist");
            }
            lists1.add(hotelDTOS.stream().filter(hotelDTO -> matchwithDestination(params.get("destination"), hotelDTO)).collect(Collectors.toList()));
        }
        for (List<HotelDTO> arr : lists1) {
            for (HotelDTO dto : arr) {
                result.add(dto);
            }
        }

        total = result.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(hotelDTOLongEntry -> hotelDTOLongEntry.getValue() > lists1.size() - 1).map(hotelDTOLongEntry -> hotelDTOLongEntry.getKey()).collect(Collectors.toList());

        return  total;

    }
// Method to check if destination is according to hotel city
    private boolean matchwithDestination(String destination, HotelDTO hotelDTO){
        boolean is = hotelDTO.getCity().equals(destination);
        return is;
    }
    // Method to get hotels with specific destination
    private List<HotelDTO> getByDestination(String destination) {
        List<HotelDTO> hotels = loadData();
        return hotels.stream().filter(hotelDTO -> matchwithDestination(destination,hotelDTO)).collect(Collectors.toList());
    }
// Check if dates are correct according to hotel selected
    private boolean matchWithDates(String dateFrom, String dateTo, HotelDTO hotelDTO) {
        boolean is;
        if(convertDate(hotelDTO.getAvailableFrom()).isBefore(convertDate(dateFrom)) || convertDate(hotelDTO.getAvailableFrom()).isEqual(convertDate(dateFrom)) && convertDate(hotelDTO.getAvailableFrom()).isAfter(convertDate(dateTo))){
            if(convertDate(hotelDTO.getAvailableTo()).isAfter(convertDate(dateTo))||convertDate(hotelDTO.getAvailableTo()).isEqual(convertDate(dateTo)) && convertDate(hotelDTO.getAvailableTo()).isBefore(convertDate(dateFrom))){
                is = true;
            }
            else
                is = false;

        }
        else
            is = false;
        return is;
    }
    //Method to convert date from String to LocalDate
    private LocalDate convertDate(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CharSequence seq = date;
        LocalDate localDate = LocalDate.parse(seq,formatter);
        return localDate;
    }

// Method that read JSON with all hotels
    private List<HotelDTO> loadData()  { File file = null;
        try{
            file = ResourceUtils.getFile("classpath:dbHotels.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HotelDTO>> typeRef = new TypeReference<>() {};
        List<HotelDTO> hotelDTOS = null;

        try {
            hotelDTOS = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return hotelDTOS;

    }
}
