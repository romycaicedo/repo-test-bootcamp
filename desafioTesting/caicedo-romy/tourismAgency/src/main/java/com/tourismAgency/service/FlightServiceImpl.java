package com.tourismAgency.service;

import com.tourismAgency.dto.*;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.FlightRepository;
import com.tourismAgency.repository.UserRepository;
import com.tourismAgency.utils.DateValidator;
import com.tourismAgency.utils.DateValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    public FlightServiceImpl(FlightRepository flightRepository, UserRepository userRepository){
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateValidator validator = new DateValidatorImpl(formatter);

    @Override
    public List<FlightDTO> getAll(Map<String, String> params) throws InvalidDateException, DestinationNotFoundException, FiltersException, DataNotFoundException {
        List<FlightDTO> flights = new ArrayList<>();
        if (params.size() == 0)
            flights = flightRepository.getAll();
        else if (validator.isValid(params.get("dateFrom")) && validator.isValid(params.get("dateTo"))) {
            validateDates(params.get("dateFrom"), params.get("dateTo"));
            flights = flightRepository.getByFilters(params);
        } else
            throw new InvalidDateException("Date Format must be dd/mm/yyyy");
        if (flights.size() == 0 && params.size() != 0) {
            throw new FiltersException("Search parameters used did not give result, please try again");
        }
        if (flights.size() == 0 && params.size() == 0) {
            throw new DataNotFoundException("We are having problems to consult the available hotels, please try again later or contact the application admin");
        }
        return flights;

    }

    public FlightReservationResponseDTO booking(FlightReservationRequestDTO booking) throws UserNotFoundException, InvalidDateException, FlightNotFoundException, PeopleAmountException, DestinationNotFoundException, InvalidEmailException, DuesException {
        FlightReservationResponseDTO response = new FlightReservationResponseDTO();
        BookingFlightDTO bookingResp = new BookingFlightDTO();
        StatusDTO statusDTO = new StatusDTO();
        Map<String, Double> paymentCalc = new HashMap<>();
        validateEmail(booking.userName);
        if (userRepository.userExist(booking.getUserName())) {
            if (validator.isValid(booking.flightReservation.dateFrom) && validator.isValid(booking.flightReservation.dateTo)) {
                validateDates(booking.flightReservation.dateFrom, booking.flightReservation.dateTo);
                if (flightRepository.matchWithFlightNumber(booking.flightReservation.flightNumber)) {
                    if (flightRepository.validateAvailability(booking.flightReservation.flightNumber, booking.flightReservation.dateFrom, booking.flightReservation.dateTo)) {
                        if (booking.flightReservation.seats == booking.flightReservation.people.size()) {
                            if (flightRepository.matchWithSeatType( booking.flightReservation.seatType,booking.flightReservation.flightNumber)) {
                                if (flightRepository.validateDestination(booking.flightReservation.flightNumber, booking.flightReservation.destination,booking.flightReservation.origin)) {
                                    long price = flightRepository.getPrice(booking.flightReservation.flightNumber);
                                    paymentCalc = paymentCalculation(booking.flightReservation.getPaymentMethod(), price, booking.flightReservation.getSeats());
                                    response.setUserName(booking.getUserName());
                                    response.setAmount(paymentCalc.get("amount"));
                                    response.setInterest(paymentCalc.get("interest"));
                                    response.setTotal(paymentCalc.get("total"));
                                    bookingResp.setDateFrom(booking.getFlightReservation().getDateFrom());
                                    bookingResp.setDateTo(booking.getFlightReservation().getDateTo());
                                    bookingResp.setOrigin(booking.getFlightReservation().getOrigin());
                                    bookingResp.setDestination(booking.getFlightReservation().getDestination());
                                    bookingResp.setFlightNumber(booking.getFlightReservation().getFlightNumber());
                                    bookingResp.setSeats(booking.getFlightReservation().getSeats());
                                    bookingResp.setSeatType(booking.getFlightReservation().getSeatType());
                                    bookingResp.setPeople(booking.getFlightReservation().getPeople());
                                    response.setFlightReservation(bookingResp);
                                    statusDTO.setCode(200);
                                    statusDTO.setMessage("Successfull Flight Reservation");
                                    response.setStatus(statusDTO);

                                } else
                                    throw new DestinationNotFoundException("Invalid destination ");
                            } else
                                throw new FlightNotFoundException("Flight requested and seats types don't match");
                        } else
                            throw new PeopleAmountException("Seat size and people data mismatch");

                    } else
                        throw new InvalidDateException("Flight is not available in selected dates");

                } else
                    throw new FlightNotFoundException("Flight not found");

            } else
                throw new InvalidDateException("Date format must be dd/mm/yyyy");
        } else
            throw new UserNotFoundException("Invalid User");
        return response;
    }

    private LocalDate convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CharSequence seq = date;
        LocalDate localDate = LocalDate.parse(seq, formatter);
        return localDate;
    }

    private void validateDates(String dateFrom, String dateTo) throws InvalidDateException {
        if (!convertDate(dateFrom).isBefore(convertDate(dateTo))) {
            throw new InvalidDateException("Start date must be less than the end date");

        }
    }
    private void validateEmail(String email) throws InvalidEmailException {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex)) {
            throw new InvalidEmailException("Invalid email format");
        }
    }

    private Map<String, Double> paymentCalculation(PaymentDTO payment, long price, int seats) throws DuesException {
        Map<String, Double> paymentInfo = new HashMap<>();
        long amount = seats*price;
        int initialDues = 3;
        int maxDues = 36;
        if (payment.type.equals("DEBIT")) {
            if (payment.dues == 1) {
                paymentInfo.put("interest", 0.0);
                String valor = String.valueOf(amount);
                double total = Double.parseDouble(valor);
                paymentInfo.put("amount", total);
                paymentInfo.put("total", total);
            } else
                throw new DuesException("Dues for Debit card must be 1");
        } else if (payment.type.equals("CREDIT")) {
            if (payment.dues == initialDues) {
                paymentInfo.put("interest", 5.0);
                String valor = String.valueOf(amount);
                double totalInterest = Double.parseDouble(valor) * 0.5;
                double total = Double.parseDouble(valor) + totalInterest;
                paymentInfo.put("amount", Double.parseDouble(valor));
                paymentInfo.put("total", total);

            } else if (payment.dues > initialDues && payment.dues <= initialDues * 2) {
                paymentInfo.put("interest", 10.0);
                String valor = String.valueOf(amount);
                double totalInterest = Double.parseDouble(valor) * 0.10;
                double total = Double.parseDouble(valor) + totalInterest;
                paymentInfo.put("amount", Double.parseDouble(valor));
                paymentInfo.put("total", total);

            } else if (payment.dues > initialDues && payment.dues <= initialDues * 3) {
                paymentInfo.put("interest", 15.0);
                String valor = String.valueOf(amount);
                double totalInterest = Double.parseDouble(valor) * 0.15;
                double total = Double.parseDouble(valor) + totalInterest;
                paymentInfo.put("amount", Double.parseDouble(valor));
                paymentInfo.put("total", total);
            } else
                throw new DuesException("Max dues allowed are 9, please check again");

        } else throw new DuesException("Card Type not Allowed");

        return paymentInfo;
    }

}
