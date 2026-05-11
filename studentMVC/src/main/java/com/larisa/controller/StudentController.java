package com.larisa.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larisa.dto.Country;
import com.larisa.dto.Course;
import com.larisa.dto.CourseStudent;
import com.larisa.dto.CourseStudentSave;
import com.larisa.dto.DeleteStudentQualifications;
import com.larisa.dto.District;
import com.larisa.dto.State;
import com.larisa.dto.Student;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
import com.larisa.dto.UserRole;
import com.larisa.dto.UserStudent;
import com.larisa.service.ClassCourseService;
import com.larisa.service.CountryService;
import com.larisa.service.CourseService;
import com.larisa.service.CourseStudentService;
import com.larisa.service.DistrictService;
import com.larisa.service.QualificationService;
import com.larisa.service.StateService;
import com.larisa.service.StudentQualificationService;
import com.larisa.service.StudentService;
import com.larisa.service.UserService;
import com.larisa.service.UserStudentService;
import com.twilio.Twilio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Controller
@RequestMapping("/student")
public class StudentController {

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
	private StudentQualificationService studentQualificationService;
	@Autowired
	private ClassCourseService classCourseService;
	@GetMapping(value = "/addCourseForm/{stId}")
	public ModelAndView courseForm(@PathVariable("stId") Long stID) {
		Student student = studentService.getStudent(stID);
		ModelAndView modelAndView = new ModelAndView("courseForm");
		modelAndView.addObject("courses", courseService.getCoursesNotTakenByStudent(student));
		modelAndView.addObject("stId", stID);
		CourseStudentSave courseStudent = new CourseStudentSave();
		modelAndView.addObject("courseStudent", courseStudent);

		return modelAndView;

	}

	@GetMapping(value = "/deleteCourseStudent/{stId}/{courseId}/{page}")
	public ModelAndView deleteCourseStudent(@PathVariable("stId") Long stID, @PathVariable("courseId") Long courseID,
			@PathVariable("page") int page) {
		ModelAndView modelAndView = new ModelAndView("");
		Student student = studentService.getStudent(stID);
		System.out.println("From delete statement " + student + " " + student.getEmail());
		String deleteStatuString = courseStudentService.deleteCourseStudent(stID, courseID);
		if (deleteStatuString.trim() != "") {
			System.out.println("in if statement");
			modelAndView.setViewName("redirect:/student/studentPage/" + student.getEmail() + "/"
					+ (page == 0 ? 1 : page) + "?deleteSuccess=true");
			return modelAndView;

		} else {
			modelAndView.setViewName("redirect:/student/studentPage/" + student.getEmail() + "/"
					+ (page == 0 ? 1 : page) + "?deleteFailed=true");

			return modelAndView;

		}

	}

	@PostMapping(value = "/saveStudentCourse")
	public ModelAndView saveStudentCourse(@ModelAttribute CourseStudentSave courseStudent) {
		Student student = studentService.getStudent(courseStudent.getStId());

		ModelAndView modelAndView = new ModelAndView(

				"redirect:/student/studentPage/" + student.getEmail() + "/1?CourseAddsuccess=true");
		String savedCourseStudentString;
		try {
			savedCourseStudentString = courseStudentService.saveCourseStudent(courseStudent.getStId(),
					courseStudent.getCourse());
			if (savedCourseStudentString.trim() != "") {
				modelAndView.addObject("courseStudent", courseStudent);

				return modelAndView;
			} else {
				modelAndView
						.setViewName("redirect:/student/studentPage/" + student.getEmail() + "\1?AddCourseerror=true");
				return modelAndView;

			}
		} catch (DuplicateKeyException e) {
			modelAndView.setViewName("redirect:/student/studentPage/" + student.getEmail() + "?duplicateCourse=true");
			return modelAndView;
			// TODO Auto-generated catch block
		}

	}

	@GetMapping("/studentPage/{email}/{page}")
	public ModelAndView studentPage(@PathVariable("page") int page, @PathVariable("email") String email) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			System.out.println("In studentPage function " + email);
			Student student = studentService.findStudentByEmail(email);
			int pageSize = 5;
			Pageable pageable = PageRequest.of(page - 1, pageSize);
			CourseStudentSave courseStudent = new CourseStudentSave();
			Page<Course> coursesPage = courseService.getCoursesByStudent(student, pageable);
			modelAndView.addObject("coursesToAdd", courseService.getCoursesNotTakenByStudent(student));

			modelAndView.addObject("user", userStudentService.getUserStudentbyEmail(email));
			modelAndView.addObject("courses", coursesPage.getContent());
			modelAndView.addObject("currentPage", page);
			modelAndView.addObject("pageSize", pageSize);
			modelAndView.addObject("totalItems", coursesPage.getTotalElements());
			modelAndView.addObject("totalPages", coursesPage.getTotalPages());
			modelAndView.addObject("student", student);
			modelAndView.setViewName("studentPage");
			modelAndView.addObject("courseStudent", courseStudent);
		} catch (Exception e) {
			// TODO: handle exception
			modelAndView.setViewName("redirect:/?InternalServerError=true");
		}
		return modelAndView;
	}

	@GetMapping("/studentHomepage/{email}")
	public ModelAndView studentHomepage(@PathVariable("email") String email) {
		Student student = studentService.findStudentByEmail(email);
		Long numberOfCourses = studentService.getCountCourses(student.getId());

		ModelAndView modelAndView = new ModelAndView("studentHomepage");
		modelAndView.addObject("noOfCourses", numberOfCourses);
		modelAndView.addObject("user", userStudentService.getUserStudentbyEmail(email));
		modelAndView.addObject("classes",classCourseService.getStudentClasses(student.getId()));
		return modelAndView;
	}

	@GetMapping("/studentViewRoutine/{id}")
	public ModelAndView studentViewRoutine(@PathVariable("id") Long id) {

		ModelAndView modelAndView = new ModelAndView("studentViewRoutine");
		
		modelAndView.addObject("classes",classCourseService.getStudentClasses(id));
		return modelAndView;
	}

	@GetMapping(value = "/updatestudent/{id}")
	public ModelAndView updateStudent(@PathVariable("id") long id) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		Student student = studentService.getStudent(id);
		UserStudent userStudent = userStudentService.getUserStudentbyEmail(student.getEmail());
		System.out.println(userStudent.getPermCountryCode() + " " + userStudent.getPermStateCode());
		List<Country> countries = countryService.getCountries();
		List<State> permStates = stateService.getStatesByCountry_code(userStudent.getPermCountryCode());
		List<State> preStates = stateService.getStatesByCountry_code(userStudent.getPreCountryCode());

		ObjectMapper mapper = new ObjectMapper();
		String studentQualJson=null;
		try {
			studentQualJson = mapper
					.writeValueAsString(studentQualificationService.getStudentQualifications(userStudent.getSt_id()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelAndView.addObject("studentQualJson", studentQualJson);
		List<District> permDistricts = districtService.getDistrictsByState(userStudent.getPermStateCode());
		List<District> preDistricts = districtService.getDistrictsByState(userStudent.getPreStateCode());
		
		modelAndView.addObject("countries", countries);
		modelAndView.setViewName("studentForm");
		modelAndView.addObject("permStates", permStates);
		modelAndView.addObject("preStates", preStates);
		modelAndView.addObject("preDistricts", preDistricts);
		modelAndView.addObject("studentQual",
				studentQualificationService.getStudentQualifications(userStudent.getSt_id()));
		modelAndView.addObject("user", userStudentService.getUserStudentbyEmail(student.getEmail()));
		modelAndView.addObject("qualifications", qualificationService.getQualifications());
		System.out.println(qualificationService.getQualifications());
		modelAndView.addObject("permDistricts", permDistricts);
		modelAndView.addObject("student", userStudent);
		modelAndView.addObject("deleteStQual", new DeleteStudentQualifications());
		return modelAndView;
	}

}
