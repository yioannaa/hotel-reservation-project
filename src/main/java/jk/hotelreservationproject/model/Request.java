package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime request_date = LocalDateTime.now();
    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;
    private String email;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Arrival date cannot be blank")
    private LocalDateTime firstDay;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Departure date cannot be blank")
    private LocalDateTime lastDay;
    private int numberOfGuests;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category roomCategory;

    public void setFirstDay(String firstDay) {
        String [] firstDayStr = firstDay.split("/");
        this.firstDay = LocalDateTime.of(Integer.parseInt(firstDayStr[2]), Integer.parseInt(firstDayStr[0]),Integer.parseInt(firstDayStr[1]),0,0 );
        this.firstDay.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
    }

    public void setLastDay(String lastDay) {
        String [] lastDayStr = lastDay.split("/");
        this.lastDay = LocalDateTime.of(Integer.parseInt(lastDayStr[2]), Integer.parseInt(lastDayStr[0]),Integer.parseInt(lastDayStr[1]),0,0 );
        this.lastDay.format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
    }

}
