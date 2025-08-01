package com.example.student.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.student.model.User;
import com.example.student.model.UserList;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	@Query("select new com.example.student.model.UserList(u.firstName,u.lastName,u.email,r.name,u.id) from User u join u.roles r")
	public List<UserList> userLists();
	}
  