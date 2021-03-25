import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

public class Fecha {

    GregorianCalendar calendar = new GregorianCalendar();


    public Fecha() {
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;

    }

    public Fecha fecha(GregorianCalendar calendar) {
        this.calendar = calendar;
        return  fecha(calendar);
    }

    public boolean validar( SimpleDateFormat sdfrmt){

        sdfrmt.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try
        {
            Date javaDate = sdfrmt.parse(strDate);
            System.out.println(strDate+" is valid date format");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            System.out.println(strDate+" is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;


    }

    public static void convert(
            GregorianCalendar gregorianCalendarDate)
    {
        // Creating an object of SimpleDateFormat
        SimpleDateFormat formattedDate
                = new SimpleDateFormat("dd-MMM-yyyy");

        // Use format() method to change the format
        // Using getTime() method,
        // this required date is passed
        // to format() method
        String dateFormatted
                = formattedDate.format(
                gregorianCalendarDate.getTime());

        // Displaying grogorian date ia SimpleDateFormat
        System.out.print("SimpleDateFormat: "
                + dateFormatted);
    }



    @Override
    public String toString() {
        return calendar.toString();
    }
}
