package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.model.User;
import com.example.student.model.wrapper.UserList;
import com.example.student.service.StudentService;
import com.example.student.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@GetMapping("/admin/adminPortal/{pageNo}")
	public String AdminPortal(HttpServletRequest httpRequest, Model model,@PathVariable(value="pageNo") int pageNo,@RequestParam("sortField") String sortField,@RequestParam("sortDir") String sortDir) {
			int pageSize=5;
		System.out.println(pageNo);
		Principal principal = httpRequest.getUserPrincipal();
		Page<UserList> users =userService.userLists(pageNo,pageSize,sortField,sortDir);
		model.addAttribute("users",users);
		model.addAttribute("user", principal.getName());
			model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",users.getTotalPages());
		model.addAttribute("totalItems",users.getTotalElements());
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

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
		System.out.println("Password "+user.getPassword());
		userService.updateUser(user);
		return "redirect:adminPortal";
	}
	@GetMapping("/admin/"
			+ "deleteUserPage/{id}")
	public String deleteUserPage(HttpServletRequest httpRequest,@PathVariable("id") long id, Model model) {
		userService.deleteUser(id);
		return "redirect:/admin/adminPortal/1?sortField=email&sortDir=asc";
	}
}
