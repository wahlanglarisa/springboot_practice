package com.larisa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	@GetMapping("/homepage")
	public ModelAndView homepage(HttpServletRequest httpServletRequest) {
		return new ModelAndView("professorHompage").addObject("message","Hello "+httpServletRequest.getUserPrincipal().getName() );
	}
}
