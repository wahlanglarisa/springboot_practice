package com.example.hotel_room_management.hotel_room_management;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.repository.RoleRepository;
import com.example.hotel_room_management.hotel_room_management.repository.UserRepository;

@SpringBootApplication
public class HotelRoomManagementApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository repository;
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HotelRoomManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println(repository.findByName("Admin"));
		// User user = new User("Larisa", "Wahlang", "admin@gmail.com", bCryptPasswordEncoder.encode("admin123"));
		// user.setRole(repository.findByName("Admin"));
		// userRepository.save(user);

	}

}
