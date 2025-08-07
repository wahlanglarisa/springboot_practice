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

import com.example.student.model.AttendancePage;
import com.example.student.model.FindProfessorClasses;
import com.example.student.model.ProfListClasses;
import com.example.student.model.StudentClass;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private CourseService courseService;

	@GetMapping("/professor/professorHomepage")
	public String profHomepage(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		List<FindProfessorClasses> professorClasses = professorService.findProfessorClasses(principal.getName());
		model.addAttribute("user", principal.getName());
		List<ProfListClasses> profListClasses = professorService.getClass_Courses(principal.getName());
		model.addAttribute("classCount", professorClasses);
		model.addAttribute("routines", profListClasses);

		return "professorHomepage";

	}

	@GetMapping("/professor/attendancePage/{id}")
	private String attendancePage(Model model, HttpServletRequest httpServletRequest, @PathVariable("id") long id) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		List<AttendancePage> attendancePages = professorService.getAttendancePages(email, id);
		model.addAttribute("course", courseService.findById(attendancePages.getFirst().getCourseID()));
		System.out.println(attendancePages.getFirst().getClass_id());
		model.addAttribute("user", email);

		model.addAttribute("students", attendancePages);
		model.addAttribute("class", new com.example.student.model.saveAttendance());
		System.out.println(courseService.findById(attendancePages.getFirst().getCourseID()));
		System.out.println(id + " " + attendancePages);
		return "attendancePage";

	}

	@PostMapping("/professor/saveAttendance/")
	private String saveAttendance(@ModelAttribute("class") com.example.student.model.saveAttendance studentClass) {
		System.out.println(studentClass.getStudentClasses().getFirst().getClass_Course().getId());
		String str=professorService.saveAttendance(studentClass);
		System.out.println(str);
		return "redirect:/professor/professorHomepage";
	}

}
