package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.ClassTime;
import com.example.student.model.Course;
import com.example.student.model.Student;
import com.example.student.model.wrapper.GetAvailClassTime;
import com.example.student.model.wrapper.ProfListClasses;
import com.example.student.model.wrapper.StudentRoutine;
import com.example.student.model.wrapper.UserList;
import com.example.student.service.ClassTimeService;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;
import com.example.student.service.StudentService;
import com.example.student.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AJAXController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClassTimeService classTimeService;

	@RequestMapping(value = "/admin/"
			+ "getDepartmentAJAX/", method = RequestMethod.GET)
	public List<String> getDepartmentAJAX(HttpServletRequest httpRequest, @RequestParam("id") long id) {
		System.out.println(id);
		List<String> courses = courseService.findbyDepartmentID(id);
		System.out.println(courses);
		return courses;
	}

	@RequestMapping(value = "/professor/viewRoutine/", method = RequestMethod.GET)
	public List<ProfListClasses> viewRoutinePage(Model model, @RequestParam("day") String day,
			HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		List<ProfListClasses> profListClasses = professorService.getProfClass_Courses_By_Day(email, day);
		System.out.println(profListClasses);
		model.addAttribute("routine", profListClasses);
		model.addAttribute("day", day);
		System.out.println(day);
		return profListClasses;
	}

	@RequestMapping(value="/student/viewRoutinePage/",method = RequestMethod.GET)
	public List<StudentRoutine> viewRoutinePage(Model model, HttpServletRequest httpServletRequest,
			@RequestParam("day") String day) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		Student student = studentService.findStudentByEmailId(email);
		List<StudentRoutine> studentRoutines = studentService.getStudentRoutines(email, day, student.getSemester(),
				student.getBranch().getId());
		model.addAttribute("routine", studentRoutines);
		return studentRoutines;

		}
	@RequestMapping("/admin/checkExistingEmail/")
	public Boolean checkEmail(Model model, HttpServletRequest httpServletRequest,@RequestParam("email") String email)  {
		Principal principal = httpServletRequest.getUserPrincipal();
		// Student student = studentService.findStudentByEmailId(email);
		// List<StudentRoutine> studentRoutines = studentService.getStudentRoutines(email, day, student.getSemester(),
		// 		student.getBranch().getId());
		// model.addAttribute("routine", studentRoutines);
		return userService.checkExsistingEmail(email);

	}
	@RequestMapping(value = "/hod/getClassTime/", method = RequestMethod.GET)
	public List<GetAvailClassTime> getClassTime(Model model, HttpServletRequest httpServletRequest,
			@RequestParam("day") String day,@RequestParam("br_id") long br_id,@RequestParam("semester") long semester) {
		
		return classTimeService.getAvailClassTimes(day,br_id,semester);

	}
	// @RequestMapping("/admin/adminPortalAjax/{pageNo}")
	// public List<UserList> AdminPortal(HttpServletRequest httpRequest, Model model,
	// 		@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
	// 		@RequestParam("sortDir") String sortDir, @RequestParam("role") String role) {
	// 	int pageSize = 10;
	// 	System.out.println(pageNo);
	// 	Principal principal = httpRequest.getUserPrincipal();
	// 	List<UserList> users = userService.userListsFilteredByRole(pageNo, pageSize, sortField, sortDir, role);

	// 	System.out.println(users);
	// 	return users;
	// }

}
