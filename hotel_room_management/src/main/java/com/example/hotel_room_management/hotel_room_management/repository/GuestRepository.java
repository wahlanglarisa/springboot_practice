package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel_room_management.hotel_room_management.model.Guest;

public interface GuestRepository extends JpaRepository<Guest,Long>{

}
