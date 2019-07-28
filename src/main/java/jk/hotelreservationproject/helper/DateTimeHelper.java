package jk.hotelreservationproject.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public LocalDate parseDate(String dateToParse){
        String [] dayStr = dateToParse.split("/");
        LocalDate parsedDate = LocalDate.of(Integer.parseInt(dayStr[2]), Integer.parseInt(dayStr[0]),Integer.parseInt(dayStr[1]));
        parsedDate.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
        return parsedDate;
    }

}
