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
    private User user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Arrival date cannot be blank")
    private LocalDateTime firstDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Departure date cannot be blank")
    private LocalDateTime lastDay;
    private int numberOfGuests;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category roomCategory;

}
