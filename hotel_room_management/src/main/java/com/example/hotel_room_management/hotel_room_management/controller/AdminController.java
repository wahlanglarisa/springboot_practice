package com.example.hotel_room_management.hotel_room_management.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotel_room_management.hotel_room_management.model.wrapper.UserList;
import com.example.hotel_room_management.hotel_room_management.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired

    private UserService userService;
    @GetMapping("/admin/adminPortal/{pageNo}")
	public String AdminPortal(HttpServletRequest httpRequest, Model model, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,@RequestParam("role") String role) {
		int pageSize = 5;
		System.out.println(pageNo);
		Principal principal = httpRequest.getUserPrincipal();
		Page<UserList> users = null;
		System.out.println("Role "+role);
		if(!(role.isEmpty())){
			System.out.println(!(role.isEmpty()));
			users=userService.userListsFilteredByRole(pageNo, pageSize, sortField, sortDir, role);
		}
		else{
			users = userService.findUsers(pageNo, pageSize, sortField, sortDir);
		}
		model.addAttribute("users", users);
		model.addAttribute("user", principal.getName());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", users.getTotalPages());
		model.addAttribute("totalItems", users.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("role",role);
		return "adminPortal";
	}
}
