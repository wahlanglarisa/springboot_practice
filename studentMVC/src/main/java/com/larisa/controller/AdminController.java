package com.larisa.controller;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.larisa.dto.ClassDay;
import com.larisa.dto.Class_Course;
import com.larisa.dto.Student;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
import com.larisa.dto.UserStudent;
import com.larisa.service.ClassDayService;
import com.larisa.service.CountryService;
import com.larisa.service.CourseService;
import com.larisa.service.CourseStudentService;
import com.larisa.service.DistrictService;
import com.larisa.service.ProfessorService;
import com.larisa.service.QualificationService;
import com.larisa.service.StateService;
import com.larisa.service.StudentService;
import com.larisa.service.UserService;
import com.larisa.service.UserStudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserStudentService userStudentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseStudentService courseStudentService;
	@Autowired
	private StateService stateService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private ClassDayService classDayService;
	@Autowired
	private ProfessorService professorService;
	@GetMapping(value = "/listStudent/{page}")
	public ModelAndView listStudent(@PathVariable("page") int page) {

		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		try {
			Page<Student> students = studentService.getListOfStudents(pageable);
			System.out.println(students);
			modelAndView.setViewName("student");
			modelAndView.addObject("students", students.getContent());
			modelAndView.addObject("currentPage", page);
			modelAndView.addObject("pageSize", pageSize);
			modelAndView.addObject("totalItems", students.getTotalElements());
			modelAndView.addObject("totalPages", students.getTotalPages());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelAndView.setViewName("redirect:/?InternalServerError=true");
		}
		return modelAndView;
	}

	@GetMapping("/viewStudent/{id}")
	public ModelAndView viewStudent(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView("viewStudent");

		try {
			modelAndView.addObject("student", studentService.getAllStudentData(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelAndView.setViewName("redirect:/admin/listStudent/1?viewStudentError=true");
		}
		return modelAndView;

	}

	@GetMapping("/adminHomepage/")
	public ModelAndView adminHomepage(Authentication authentication) {

		ModelAndView modelAndView = new ModelAndView("adminHomepage");
		try {
			modelAndView.addObject("noOfStudents", studentService.getCountofStudents());
			modelAndView.addObject("user", userStudentService.getUserStudentbyEmail(authentication.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			modelAndView.addObject("noOfStudents", 0);
			modelAndView.addObject("user", userStudentService.getUserStudentbyEmail(authentication.getName()));
			// TODO: handle exception
		}
		return modelAndView;

	}

	@GetMapping("/addClass")
	public ModelAndView addClass() {
		return new ModelAndView("/addClass").addObject("days", classDayService.getClassDays()).addObject("cl",
				new Class_Course()).addObject("courses", courseService.getCourses()).addObject("professors", classDayService);
	}

	@GetMapping(value = "/deleteStudent/{page}/{id}")
	public ModelAndView deleteStudent(@PathVariable("id") long id, @PathVariable("page") int page) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			Student student = studentService.getStudent(id);
			studentService.deleteStudent(student);
			modelAndView
					.setViewName("redirect:/admin/listStudent/" + (page == 0 ? 1 : page) + "?userDeleteSuccess=true");
		} catch (Exception e) {
			// TODO: handle exception
			modelAndView
					.setViewName("redirect:/admin/listStudent/" + (page == 0 ? 1 : page) + "?userDeleteSuccess=false");

		}
		return modelAndView;

	}
}
