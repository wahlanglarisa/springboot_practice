package com.example.student.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
