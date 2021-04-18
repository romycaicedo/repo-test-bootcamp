package com.tourismAgency.service;

import com.tourismAgency.dto.*;
import com.tourismAgency.exceptions.*;
import com.tourismAgency.repository.HotelRepository;
import com.tourismAgency.repository.UserRepository;
import com.tourismAgency.utils.DateValidator;
import com.tourismAgency.utils.DateValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.temporal.ChronoUnit.DAYS;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Autowired
    private UserRepository userRepository;

    public HotelServiceImpl(HotelRepository repository, UserRepository userRepository) {
        this.hotelRepository = repository;
        this.userRepository = userRepository;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateValidator validator = new DateValidatorImpl(formatter);

    @Override
    public List<HotelDTO> getHotels(Map<String, String> params) throws InvalidDateException, FiltersException, DataNotFoundException, DestinationNotFoundException {
        List<HotelDTO> hoteles = new ArrayList<>();

        if (params.size() == 0)
            hoteles = hotelRepository.getAll();
        else if (validator.isValid(params.get("dateFrom")) && validator.isValid(params.get("dateTo"))) {
            validateDates(params.get("dateFrom"), params.get("dateTo"));
            hoteles = hotelRepository.getByFilters(params);
        } else
            throw new InvalidDateException("Date Format must be dd/mm/yyyy");
        if (hoteles.size() == 0 && params.size() != 0) {
            throw new FiltersException("Search parameters used did not give result, please try again");
        }
        if (hoteles.size() == 0 && params.size() == 0) {
            throw new DataNotFoundException("We are having problems to consult the available hotels, please try again later or contact the application admin");
        }
        return hoteles;
    }

    public HotelBookingResponseDTO booking(HotelBookingRequestDTO booking) throws UserNotFoundException, InvalidDateException, HotelNotFoundException, PeopleAmountException, DestinationNotFoundException, InvalidEmailException, DuesException {
        HotelBookingResponseDTO response = new HotelBookingResponseDTO();
        BookingHotelDTO bookingResp = new BookingHotelDTO();
        StatusDTO statusDTO = new StatusDTO();
        Map<String, Double> paymentCalc = new HashMap<>();
        validateEmail(booking.userName);
        validateVaccancy(booking.booking.getRoomType(), booking.booking.getPeopleAmount());
        if (userRepository.userExist(booking.getUserName())) {
            if (validator.isValid(booking.booking.dateFrom) && validator.isValid(booking.booking.dateTo)) {
                validateDates(booking.booking.dateFrom, booking.booking.dateTo);
                if (hotelRepository.validateHotel(booking.booking.hotelCode)) {
                    if (hotelRepository.validateAvailavility(booking.booking.hotelCode, booking.booking.dateFrom, booking.booking.dateTo)) {
                        if (booking.booking.peopleAmount == booking.booking.people.size()) {
                            if (hotelRepository.validateRooms(booking.booking.hotelCode, booking.booking.roomType)) {
                                if (hotelRepository.validateDestination(booking.booking.hotelCode, booking.booking.destination)) {
                                    long days = calculateNights(booking.booking.getDateFrom(), booking.booking.getDateTo());
                                    long nightPrice = hotelRepository.getNightPrice(booking.booking.hotelCode);
                                    paymentCalc = paymentCalculation(booking.booking.getPaymentMethod(), days, nightPrice);
                                    response.setUserName(booking.getUserName());
                                    response.setAmount(paymentCalc.get("amount"));
                                    response.setInterest(paymentCalc.get("interest"));
                                    response.setTotal(paymentCalc.get("total"));
                                    bookingResp.setDateFrom(booking.getBooking().getDateFrom());
                                    bookingResp.setDateTo(booking.getBooking().getDateTo());
                                    bookingResp.setDestination(booking.getBooking().getDestination());
                                    bookingResp.setHotelCode(booking.getBooking().getHotelCode());
                                    bookingResp.setPeopleAmount(booking.getBooking().getPeopleAmount());
                                    bookingResp.setRoomType(booking.getBooking().getRoomType());
                                    bookingResp.setPeople(booking.getBooking().getPeople());
                                    response.setBookingHotelDTO(bookingResp);
                                    statusDTO.setCode(200);
                                    statusDTO.setMessage("Successfull Reservation");
                                    response.setStatus(statusDTO);

                                } else
                                    throw new DestinationNotFoundException("Invalid destination ");
                            } else
                                throw new HotelNotFoundException("Hotel requested and rooms don't match");
                        } else
                            throw new PeopleAmountException("People Amount and people data mismatch");

                    } else
                        throw new InvalidDateException("Hotel is not available in selected dates");

                } else
                    throw new HotelNotFoundException("Hotel not found");

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


    private Map<String, Double> paymentCalculation(PaymentDTO payment, long days, long nigthPrice) throws DuesException {
        Map<String, Double> paymentInfo = new HashMap<>();
        long amount = nigthPrice * days;
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
                paymentInfo.put("interest", 0.05);
                String valor = String.valueOf(amount);
                double totalInterest = Double.parseDouble(valor) * 0.5;
                double total = Double.parseDouble(valor) + totalInterest;
                paymentInfo.put("amount", Double.parseDouble(valor));
                paymentInfo.put("total", total);

            } else if (payment.dues > initialDues && payment.dues <= initialDues * 2) {
                paymentInfo.put("interest", 0.10);
                String valor = String.valueOf(amount);
                double totalInterest = Double.parseDouble(valor) * 0.10;
                double total = Double.parseDouble(valor) + totalInterest;
                paymentInfo.put("amount", Double.parseDouble(valor));
                paymentInfo.put("total", total);

            } else if (payment.dues > initialDues && payment.dues <= initialDues * 3) {
                paymentInfo.put("interest", 0.15);
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


    private long calculateNights(String dateFrom, String dateTo) {
        LocalDate dateInit = convertDate(dateFrom);
        LocalDate dateEnd = convertDate(dateTo);
        long days = DAYS.between(dateInit, dateEnd);

        return days;
    }

    private void validateVaccancy(String roomType, int amount) throws PeopleAmountException {
        if (roomType.equalsIgnoreCase("Single")) {
            if (amount != 1) {
                throw new PeopleAmountException("Room selected is not correct");
            }
        }
        if (roomType.equalsIgnoreCase("Double")) {
            if (amount != 2) {
                throw new PeopleAmountException("Room selected is not correct");
            }
        }
        if (roomType.equalsIgnoreCase("Triple")) {
            if (amount != 3) {
                throw new PeopleAmountException("Room selected is not correct");
            }
        }
        if (roomType.equalsIgnoreCase("Multiple")) {
            if (amount < 4 && amount > 10) {
                throw new PeopleAmountException("Room selected is not correct");
            }
        }

    }

}
