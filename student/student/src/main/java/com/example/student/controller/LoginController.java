package com.example.student.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/student/studentHomepage")
	public String studentHomepage(HttpServletRequest httpRequest,Model model) {
		 Principal principal=httpRequest.getUserPrincipal();
		  model.addAttribute("name",principal.getName());
		return "studentHomepage";
	}
}
