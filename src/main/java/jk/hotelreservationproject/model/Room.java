package jk.hotelreservationproject.model;

import jk.hotelreservationproject.model.enums.RoomClassCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    @Enumerated
    private RoomClassCategory classCategory;
}
