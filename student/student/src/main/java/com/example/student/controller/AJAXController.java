package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.Course;
import com.example.student.model.wrapper.ProfListClasses;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;
import com.example.student.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AJAXController {
    	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;
	@RequestMapping(value="/admin/"
			+ "getDepartmentAJAX/",method = RequestMethod.GET)
	public List<String> getDepartmentAJAX(HttpServletRequest httpRequest,@RequestParam("id") long id) {
        System.out.println(id);
	List<String> courses=courseService.findbyDepartmentID(id);
    System.out.println(courses);
		return courses;
	}
		@RequestMapping(value = "/professor/viewRoutine/",method = RequestMethod.GET)
	public List<ProfListClasses> viewRoutinePage(Model model, @RequestParam("day") String day, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		List<ProfListClasses> profListClasses = professorService.getProfClass_Courses_By_Day(email, day);
		System.out.println(profListClasses);
		model.addAttribute("routine", profListClasses);
		model.addAttribute("day", day);
		System.out.println(day);
		return profListClasses;
	}
	@RequestMapping("/student/viewRoutinePage/")
	public List<StudentRoutine> viewRoutinePage(Model model, HttpServletRequest httpServletRequest,@RequestParam("day") String day) {
		Principal principal=httpServletRequest.getUserPrincipal();
		String email=principal.getName();
		List<StudentRoutine> studentRoutines=studentService.getStudentRoutines(email,day);
		model.addAttribute("routine", studentRoutines);
		return studentRoutines;	 
	
	}
	
}

