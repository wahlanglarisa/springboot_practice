package com.example.student.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.model.Branch;
import com.example.student.model.Student;
import com.example.student.model.User;
import com.example.student.model.wrapper.StudentDepartmentBranch;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.TestResults;
import com.example.student.model.wrapper.UpComingTests;
import com.example.student.model.wrapper.UserList;
import com.example.student.model.wrapper.findNoOfAttendance;
import com.example.student.service.BranchService;
import com.example.student.service.StudentService;
import com.example.student.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/student/studentHomepage")
	public String studentHomepage(HttpServletRequest httpRequest, Model model) {
		Principal principal = httpRequest.getUserPrincipal();
		List<StudentRoutine> studentRoutines = studentService.findStudentClasses(principal.getName());
		List<UpComingTests> tests = studentService.upComingTests(principal.getName());
		List<TestResults> results = studentService.testResults(principal.getName());
		StudentDepartmentBranch departmentBranch=studentService.getDepartmentBranch(principal.getName());
		findNoOfAttendance totalAttendance = studentService.noOfAttendance(principal.getName());
		model.addAttribute("user", principal.getName());
		model.addAttribute("routines", studentRoutines);
		model.addAttribute("tests", tests);
		model.addAttribute("totalAttendance", totalAttendance);
		model.addAttribute("results", results);
		model.addAttribute("stdeptBranch",departmentBranch);
		model.addAttribute("date",LocalDate.now());
		
		return "studentHomepage";
	}
	@GetMapping("/student/viewRoutinePage/{day}")
	public String viewRoutinePage(Model model, HttpServletRequest httpServletRequest,@PathVariable("day") String day) {
		Principal principal=httpServletRequest.getUserPrincipal();
		String email=principal.getName();
		Student student=studentService.findStudentByEmailId(email);
		List<StudentRoutine> studentRoutines=studentService.getStudentRoutines(email,day,student.getSemester(),student.getBranch().getId());
		model.addAttribute("routine", studentRoutines);
		return "viewRoutineStudent";	
	
	}
	

}
