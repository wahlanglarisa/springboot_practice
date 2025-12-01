package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel_room_management.hotel_room_management.model.Room;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.GetCountByRoomStatus;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    public List<Room> findByStatus(String status);

    @Query("select new com.example.hotel_room_management.hotel_room_management.model.wrapper.GetCountByRoomStatus(count(*),room.status)"
    +" from Room room where room.status=:status group by room.status")
    public GetCountByRoomStatus getCountByRoomStatus(@Param("status") String status);
}
