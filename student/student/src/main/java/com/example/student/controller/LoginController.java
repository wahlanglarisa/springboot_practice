package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.student.model.StudentRoutine;
import com.example.student.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@Autowired
	private StudentService studentService;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/student/studentHomepage")
	public String studentHomepage(HttpServletRequest httpRequest,Model model) {
		 Principal principal=httpRequest.getUserPrincipal();
		 List<StudentRoutine> studentRoutines=studentService.findStudentClasses(principal.getName());
//		 System.out.println(studentRoutines);
		 Long totalAttendance=studentService.noOfAttendance(principal.getName()).getCount();

		  model.addAttribute("routines",studentRoutines);
		  model.addAttribute("totalAttendance",totalAttendance);
		return "studentHomepage";
	}
}
