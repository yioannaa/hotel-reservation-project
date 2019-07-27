package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Email(message = "Invalid e-mail address")
    @NotBlank(message = "E-mail cannot be empty")
    private String email;
    @NotBlank
    @Size(min=6, message = "Password must have at least 6 characters")
    private String password;
    @Transient
    @NotBlank
    @Size(min=6, message = "Password must have at least 6 characters")
    private String password_confirm;
    @ManyToOne
    @JoinColumn (name ="role_id")
    private Role roleId;







}
