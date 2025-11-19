package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_room_management.hotel_room_management.model.User;
import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByEmail(String email);
}
