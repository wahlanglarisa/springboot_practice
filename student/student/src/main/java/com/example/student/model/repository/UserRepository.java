package com.example.student.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.student.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
