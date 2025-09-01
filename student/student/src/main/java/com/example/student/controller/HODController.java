package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.model.Class_Course;
import com.example.student.model.Course;
import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.Professor;
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

	@GetMapping("/hod/viewClass")
	public String viewClass(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
//		List<FindProfessorClasses> professorClasses = professorService.findProfessorClasses(principal.getName());
//		model.addAttribute("user", principal.getName());
//		List<ProfListClasses> profListClasses = professorService.getClass_Courses(principal.getName());
//		model.addAttribute("classCount", professorClasses);
//		model.addAttribute("routines", profListClasses);
		Professor professor = professorService.getProfByEmail(principal.getName());
		List<ProfListClasses> profListClasses = professorService
				.getDeptClass_Courses(professor.getDepartment().getId());

		System.out.println("Department ID: " + professor.getDepartment().getId() + "List: " + profListClasses);
		model.addAttribute("routines", profListClasses);
		return "viewClassList";

	}

	@GetMapping("/hod/addNewClassPage")
	public String addClassPage(Model model) {
		List<Professor> professors = professorService.getProfessors();
		List<Course> courses = courseService.findAllCourses();
		System.out.println(courses);
		model.addAttribute("courses", courses);
		model.addAttribute("professors",professors);
		model.addAttribute("class", new Class_Course());
		return "addNewClass";
	}

	@PostMapping("/hod/saveClass")
	public String saveClass(@ModelAttribute("class") Class_Course class_Course,HttpServletRequest httpServletRequest) {
		System.out.println("Course Name " + class_Course.getCourse_class().getCourseName() + " Course ID "
				+ class_Course.getCourse_class().getId());
		System.out.println(class_Course.getProfessor().getFirstName()+" "+class_Course.getProfessor().getLastName());
		
		return "redirect:/hod/addNewClassPage";
	}
}