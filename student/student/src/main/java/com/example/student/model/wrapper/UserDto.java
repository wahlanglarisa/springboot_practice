package com.example.student.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public
 class UserDto {

	private long ID;
	private  String firstName;
	private String lastName;
	private String emailID;
	private List<String> courses=new ArrayList<String>();
	private String password;
	private long semester;
	private long dept_id;
	private long branch_id;
	private boolean hod;
}

