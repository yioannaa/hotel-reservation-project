package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    @Email (message = "Invalid e-mail address")
    @NotBlank(message = "E-mail cannot be empty")
    private String email;
    private String phone;
    @NotBlank
    @Size(min=6, message = "Password must have at least 6 characters)")
    private String password;
    @Transient
    @NotBlank
    @Size(min=6, message = "Password must have at least 6 characters)")
    private String password_confirm;
    private LocalDateTime registration_date = LocalDateTime.now();

}
