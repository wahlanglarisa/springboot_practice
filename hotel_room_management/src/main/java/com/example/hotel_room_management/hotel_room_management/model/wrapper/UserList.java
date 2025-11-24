package com.example.hotel_room_management.hotel_room_management.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
	private String firstName;
	private String lastName;
	private String email;
	private String roleName;
	private long id;

}
