package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel_room_management.hotel_room_management.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{

}
