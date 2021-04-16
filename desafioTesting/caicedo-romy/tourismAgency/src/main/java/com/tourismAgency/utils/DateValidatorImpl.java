package com.tourismAgency.utils;

import lombok.AllArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
public class DateValidatorImpl implements DateValidator{
    private DateTimeFormatter dateFormatter;

    @Override
    public boolean isValid(String date) {
        try {
            LocalDate.parse(date,dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
