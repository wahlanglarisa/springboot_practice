package com.example.hotel_room_management.hotel_room_management.service;

import java.util.List;

import com.example.hotel_room_management.hotel_room_management.model.Room;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.GetCountByRoomStatus;

public interface RoomService {
    public List<Room> findByStatus(String status);
    public GetCountByRoomStatus getCountByRoomStatus(String status);
}
