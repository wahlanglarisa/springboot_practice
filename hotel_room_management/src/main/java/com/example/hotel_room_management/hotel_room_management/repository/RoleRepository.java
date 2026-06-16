package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel_room_management.hotel_room_management.model.Role;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    public Role findByName(String name);
}
