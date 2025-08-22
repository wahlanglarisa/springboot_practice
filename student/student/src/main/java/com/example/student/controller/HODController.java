package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HODController {
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private CourseService courseService;

	@GetMapping("/hod/hodPortal")
	public String profHomepage(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		List<FindProfessorClasses> professorClasses = professorService.findProfessorClasses(principal.getName());
		model.addAttribute("user", principal.getName());
		List<ProfListClasses> profListClasses = professorService.getClass_Courses(principal.getName());
		model.addAttribute("classCount", professorClasses);
		model.addAttribute("routines", profListClasses);

		return "hodPortal";

	}
}