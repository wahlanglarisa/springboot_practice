package com.example.student.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.exception.EmptyUserException;
import com.example.student.model.Branch;
import com.example.student.model.wrapper.UserDto;
import com.example.student.service.BranchService;
import com.example.student.service.CourseService;
import com.example.student.service.DepartmentService;
import com.example.student.service.ProfessorService;
import com.example.student.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/registerStudent")
	public String registerStudentPage(Model model) {
		Collection<String> courseList = courseService.findCourses();
		UserDto userDto = new UserDto();
		userDto.getCourses().add("");
		List<Branch> branches = branchService.findBranchList();

		model.addAttribute("branches", branches);
		model.addAttribute("user", new UserDto());
		model.addAttribute("courseList", courseList);
		System.out.println("Hello" + courseList.toString());
		return "registerStudent";
	}

	@GetMapping("/registerProfessor")
	public String registerProfessor(Model model) {
		Collection<String> courseList = courseService.findCourses();
		UserDto userDto = new UserDto();
		userDto.getCourses().add("");
		model.addAttribute("user", new UserDto());
		model.addAttribute("courseList", courseList);
		model.addAttribute("department", departmentService.findAllDepartments());
		System.out.println("Hello" + courseList.toString());
		return "registerProfessor";
	}

	@PostMapping("/studentData")
	public String GetStudentData(@ModelAttribute("user") UserDto user, Model model) {
		System.out.println("From /studentData " + user.getCourses());

		studentService.saveStudent(user);
		return "redirect:/registerStudent?success";
	}

	@PostMapping("/professorData")
	public String GetProfessorData(@ModelAttribute("user") UserDto user, Model model) {
		System.out.println("From /studentData " + user.getDept_id() + user.getCourses());
		try {
			professorService.saveProfessor(user);
			return "redirect:/registerProfessor?success";

		} catch (DataIntegrityViolationException dIntegrityViolationException) {
			return "redirect:/registerProfessor?dIntegrityViolationException";

		}
		catch(EmptyUserException emptyUserException){
			return "redirect:/registerProfessor?emptyDetailsException";
		}
		// studentService.saveStudent(user);
	}
}
