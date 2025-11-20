package com.example.hotel_room_management.hotel_room_management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByEmail(String email);
    @Query("select new com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList(u.firstName,u.lastName,u.email,r.name,u.id) from User u join u.role r")
	public Page<UserList> userLists(Pageable pageable);
    @Query("select new com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList(u.firstName,u.lastName,u.email,r.name,u.id) from User u join u.role r where r.name=:role")
	public Page<UserList> userListsRoleFiltered(Pageable pageable,@Param("role") String roleName);
}
