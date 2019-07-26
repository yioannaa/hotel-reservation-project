package jk.hotelreservationproject.repository;

import jk.hotelreservationproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findRoomByCategoryId (Long category_id); // tego też chyba na razie nie potrzebuję
}
