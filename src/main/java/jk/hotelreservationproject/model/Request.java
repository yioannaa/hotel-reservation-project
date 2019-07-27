package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jk.hotelreservationproject.validation.ValidateDateRange;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ValidateDateRange(start = "firstDay", end = "lastDay", message = "{validation.date.range_error}", equal = true)
public class Request{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime request_date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    private String email;
    @FutureOrPresent(message = "Date is in past")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Arrival date cannot be blank")
    private LocalDate firstDay;
    @Future(message = "use only future date")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Departure date cannot be blank")
    private LocalDate lastDay;
    private int numberOfGuests;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category roomCategory;

    public void setFirstDay(String firstDay) {
        String [] firstDayStr = firstDay.split("/");
        this.firstDay = LocalDate.of(Integer.parseInt(firstDayStr[2]), Integer.parseInt(firstDayStr[0]),Integer.parseInt(firstDayStr[1]));
        this.firstDay.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
    }

    public void setLastDay(String lastDay) {
        String [] lastDayStr = lastDay.split("/");
        this.lastDay = LocalDate.of(Integer.parseInt(lastDayStr[2]), Integer.parseInt(lastDayStr[0]),Integer.parseInt(lastDayStr[1]));
        this.lastDay.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
    }

    public boolean isDateValid(){
        LocalDate today = LocalDate.now();
        if (firstDay.isBefore(today.plusDays(1))){
            return false;
        }
        if(lastDay.isBefore(today)){
            return false;
        }
        if (lastDay.isBefore(firstDay)){
            return false;
        }
        return true;
    }

}
