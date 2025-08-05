package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.model.User;
import com.example.student.model.UserList;
import com.example.student.service.StudentService;
import com.example.student.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@GetMapping("/admin/"
			+ "adminPortal")
	public String AdminPortal(HttpServletRequest httpRequest, Model model) {
		Principal principal = httpRequest.getUserPrincipal();
		List<UserList> users =userService.userLists();
		model.addAttribute("users",users);
		model.addAttribute("user", principal.getName());

		return "adminPortal";
	}
	@GetMapping("/admin/"
			+ "updateUserPage/{id}")
	public String updateUserPage(HttpServletRequest httpRequest,@PathVariable("id") long id, Model model) {
		Principal principal = httpRequest.getUserPrincipal();
		User user=userService.getUserById(id);
		System.out.println(user);
		model.addAttribute("user", user);

		return "updateUser";
	}
	@PostMapping("/admin/updateUser")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:adminPortal";
	}
}
