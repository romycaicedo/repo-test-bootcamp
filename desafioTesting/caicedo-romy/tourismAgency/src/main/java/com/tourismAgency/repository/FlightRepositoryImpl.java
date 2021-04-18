package com.tourismAgency.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourismAgency.dto.FlightDTO;
import com.tourismAgency.dto.HotelDTO;
import com.tourismAgency.exceptions.DestinationNotFoundException;
import com.tourismAgency.exceptions.FiltersException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    @Override
    public List<FlightDTO> getAll(){
        List<FlightDTO> flights = loadData();
        return flights;
    }

    public boolean matchWithSeatType(String seatType, String flightNumber){
        boolean match = false;
        List<FlightDTO> flightDTOS = loadData();
        for(FlightDTO dto: flightDTOS){
            if(dto.getFlightNumber().equals(flightNumber) && dto.getSeatType().equalsIgnoreCase(seatType))
                match = true;
        }
        return match;
    }

    public boolean matchWithFlightNumber(String flightNumber){
        boolean match = false;
        List<FlightDTO> flightDTOS = loadData();
        for(FlightDTO dto: flightDTOS){
            if(dto.getFlightNumber().equals(flightNumber)){
                match = true;
            }

        }
        return match;

    }

    public boolean validateAvailability(String flightNumber, String dateFrom, String dateTo){
        boolean isAvailable = false;
        List<FlightDTO> flightDTOS = loadData();
        for(FlightDTO dto: flightDTOS){
            if(dto.getFlightNumber().equals(flightNumber) && matchWithDates(dateFrom,dateTo,dto)) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    @Override
    public boolean validateDestination(String flightNumber,String destination, String origin){
        boolean match = false;
        List<FlightDTO> flightDTOS = loadData();
        for(FlightDTO dto: flightDTOS){
            if(dto.getFlightNumber().equals(flightNumber) && dto.getDestination().equals(destination) && dto.getOrigin().equals(origin)) {
                match = true;
            }
        }
        return match;
    }

    @Override
    public long getPrice(String flightNumber){
        long price = 0;
        List<FlightDTO> flightDTOS = loadData();
        for(FlightDTO dto: flightDTOS){
            if(dto.getFlightNumber().equals(flightNumber) ) {
                price = Long.parseLong(dto.getPrice());
            }
        }
        return price;
    }

    public List<FlightDTO> getByFilters(Map<String,String> params) throws DestinationNotFoundException, FiltersException {
        List<FlightDTO> flightDTOS = loadData();
        List<FlightDTO> total = new ArrayList<>();
        List<List<FlightDTO>> lists1 = new ArrayList<>();
        List<FlightDTO> result = new ArrayList<>();
        List<FlightDTO> validateDestination = new ArrayList<>();
        if (params.containsKey("dateFrom") && params.containsKey("dateTo")) {
            lists1.add(flightDTOS.stream().filter(flightDTO -> matchWithDates(params.get("dateFrom"),params.get("dateTo"),flightDTO)).collect(Collectors.toList()));
        }

        if (params.containsKey("origin")) {
            validateDestination = getByDestination(params.get("origin"));
            if(validateDestination.size()== 0){
                throw new DestinationNotFoundException("The selected origin does not exist");
            }
            lists1.add(flightDTOS.stream().filter(flightDTO -> matchWithOrigin(params.get("origin"), flightDTO)).collect(Collectors.toList()));
        }
        if (params.containsKey("destination")) {
            validateDestination = getByDestination(params.get("destination"));
            if(validateDestination.size()== 0){
                throw new DestinationNotFoundException("The selected destination does not exist");
            }
            lists1.add(flightDTOS.stream().filter(flightDTO -> matchWithDestination(params.get("destination"), flightDTO)).collect(Collectors.toList()));
        }
        if(params.get("origin").equals(params.get("destination"))){
            throw new FiltersException("Origin must be different from destination");
        }

        for (List<FlightDTO> arr : lists1) {
            for (FlightDTO dto : arr) {
                result.add(dto);
            }
        }

        total = result.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(flightDTOLongEntry -> flightDTOLongEntry.getValue() > lists1.size() - 1).map(flightDTOLongEntry -> flightDTOLongEntry.getKey()).collect(Collectors.toList());

        return  total;

    }

    private boolean matchWithDates(String dateFrom, String dateTo, FlightDTO flightDTO) {
        boolean is;
        if(convertDate(dateFrom).equals(convertDate(flightDTO.getDateFrom())) && convertDate(dateFrom).isBefore(convertDate(flightDTO.getDateTo()))){
            if(convertDate(dateTo).equals(convertDate(flightDTO.getDateTo())) && convertDate(dateTo).isAfter(convertDate(flightDTO.getDateFrom()))){
                is = true;
            }
            else
                is = false;

        }
        else
            is = false;
        return is;
    }

    private LocalDate convertDate(String date)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CharSequence seq = date;
        LocalDate localDate = LocalDate.parse(seq,formatter);
        return localDate;
    }
    private List<FlightDTO> getByDestination(String destination) {
        List<FlightDTO> flights = loadData();
        return flights.stream().filter(flightDTO -> matchWithDestination(destination,flightDTO)).collect(Collectors.toList());
    }
    private boolean matchWithDestination(String destination, FlightDTO flightDTO){
        boolean is = flightDTO.getDestination().equals(destination);
        return is;
    }

    private boolean matchWithOrigin(String origin, FlightDTO flightDTO){
        boolean is = flightDTO.getOrigin().equals(origin);
        return is;
    }

    private List<FlightDTO> getByOrigin(String origin){
        List<FlightDTO> flights = loadData();
        return flights.stream().filter(flightDTO -> matchWithOrigin(origin,flightDTO)).collect(Collectors.toList());
    }



    private List<FlightDTO> loadData()  { File file = null;
        try{
            file = ResourceUtils.getFile("classpath:dbFlights.json");
        } catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<java.util.List<FlightDTO>> typeRef = new TypeReference<>() {};
        List<FlightDTO> flightDTOS = null;

        try {
            flightDTOS = objectMapper.readValue(file, typeRef);
        }catch(Exception e){
            e.printStackTrace();
        }

        return flightDTOS;

    }
}
