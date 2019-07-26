package jk.hotelreservationproject.service;

import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.model.Room;
import jk.hotelreservationproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public void getRoomByCategoryId (Long category_id){  //na razie chyba tego nie potrzebuję
         roomRepository.findRoomByCategoryId(category_id);
    }







}
