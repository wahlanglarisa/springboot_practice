package com.example.hotel_room_management.hotel_room_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel_room_management.hotel_room_management.model.Room;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.GetCountByRoomStatus;
import com.example.hotel_room_management.hotel_room_management.repository.RoomRepository;
import com.example.hotel_room_management.hotel_room_management.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> findByStatus(String status) {
        // TODO Auto-generated method stub
        return roomRepository.findByStatus(status);
    }
    @Override
    public GetCountByRoomStatus getCountByRoomStatus(String status) {
        // TODO Auto-generated method stub
        return roomRepository.getCountByRoomStatus(status);
    }

}
