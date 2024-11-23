package org.ssischoolbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssischoolbackend.dao.RoomDao;
import org.ssischoolbackend.model.Room;

import java.util.List;

@Service
public class RoomService {
    private final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Transactional
    public Long createRoom(Room room) {
        return roomDao.createRoom(room);
    }


    public Room getRoomById(Long id) {
        return roomDao.getRoomById(id.intValue());
    }

    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Transactional
    public void updateRoom(Room room) {
        roomDao.updateRoom(room);
    }

    @Transactional
    public void deleteRoom(Long id) {
        roomDao.deleteRoom(id.intValue());
    }
}
