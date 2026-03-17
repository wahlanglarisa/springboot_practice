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

import com.larisa.dao.UserRole;
import com.larisa.dto.Country;
import com.larisa.dto.Course;
import com.larisa.dto.CourseStudent;
import com.larisa.dto.CourseStudentSave;
import com.larisa.dto.District;
import com.larisa.dto.State;
import com.larisa.dto.Student;
import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;
import com.larisa.dto.UserStudent;
import com.larisa.service.CountryService;
import com.larisa.service.CourseService;
import com.larisa.service.CourseStudentService;
import com.larisa.service.DistrictService;
import com.larisa.service.StateService;
import com.larisa.service.StudentService;
import com.larisa.service.UserService;
import com.larisa.service.UserStudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
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
	@GetMapping(value = "/")
	public ModelAndView loginPage() {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/?loggedOut=true");
	}

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
			modelAndView.setViewName("redirect:/studentPage/" + student.getEmail() + "/" + (page == 0 ? 1 : page)
					+ "?deleteSuccess=true");
			return modelAndView;

		} else {
			modelAndView.setViewName("redirect:/studentPage/" + student.getEmail() + "/" + (page == 0 ? 1 : page)
					+ "?deleteFailed=true");

			return modelAndView;

		}

	}

	@PostMapping(value = "/saveStudentCourse")
	public ModelAndView saveStudentCourse(@ModelAttribute CourseStudentSave courseStudent) {
		Student student = studentService.getStudent(courseStudent.getStId());

		ModelAndView modelAndView = new ModelAndView(

				"redirect:/studentPage/" + student.getEmail() + "/1?CourseAddsuccess=true");
		String savedCourseStudentString;
		try {
			savedCourseStudentString = courseStudentService.saveCourseStudent(courseStudent.getStId(),
					courseStudent.getCourse());
			if (savedCourseStudentString.trim() != "") {
				modelAndView.addObject("courseStudent", courseStudent);

				return modelAndView;
			} else {
				modelAndView.setViewName("redirect:/studentPage/" + student.getEmail() + "\1?AddCourseerror=true");
				return modelAndView;

			}
		} catch (DuplicateKeyException e) {
			modelAndView.setViewName("redirect:/studentPage/" + student.getEmail() + "?duplicateCourse=true");
			return modelAndView;
			// TODO Auto-generated catch block
		}

	}

	@PostMapping(value = "/loginValidate")
	public ModelAndView loginPageValidate(@ModelAttribute User user, HttpServletRequest httpServletRequest) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		UserRole userVal = userService.validateUser(user);
		if (userVal != null) {
			System.out.println(userVal.getRoleName());
			modelAndView.addObject("user", userVal);
			HttpSession session = httpServletRequest.getSession();
			if (userVal.getRoleName().equals("Student")) {
				session.setAttribute("user", userStudentService.getUserStudentbyEmail(user.getEmail()));

				modelAndView.setViewName("redirect:/studentPage/" + userVal.getEmail() + "/1");

			} else if (userVal.getRoleName().equals("Admin")) {
				session.setAttribute("user", userService.getUserByEmail(user.getEmail()));

				modelAndView.setViewName("redirect:/listStudent/1");

			}

			return modelAndView;
//			modelAndView.setViewName("redirect:/studentPage/1");

		} else {
			modelAndView.setViewName("redirect:/?loginerror=true");
			return modelAndView;

		}
	}

	@GetMapping("/studentPage/{email}/{page}")
	public ModelAndView studentPage(@PathVariable("page") int page, @PathVariable("email") String email) {
		ModelAndView modelAndView = new ModelAndView();
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
		return modelAndView;
	}

	@GetMapping(value = "/listStudent/{page}")
	public ModelAndView listStudent(@PathVariable("page") int page) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		Page<Student> students = studentService.getListOfStudents(pageable);
		System.out.println(students);
		modelAndView.setViewName("student");
		modelAndView.addObject("students", students.getContent());
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("pageSize", pageSize);
		modelAndView.addObject("totalItems", students.getTotalElements());
		modelAndView.addObject("totalPages", students.getTotalPages());
		return modelAndView;
	}

	@GetMapping(value = "/addstudent")
	public ModelAndView addStudent() {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		UserStudent student = new UserStudent();
		modelAndView.setViewName("studentForm");
		student.setSt_id(Long.parseLong("0"));
		student.setUser_id("");
		List<Country> countries = countryService.getCountries();
		System.out.println(countries);
		modelAndView.addObject("student", student);
		modelAndView.addObject("countries", countries);

		return modelAndView;
	}

	@GetMapping(value = "/updatestudent/{id}")
	public ModelAndView updateStudent(@PathVariable("id") long id) {
		System.out.println("in list student function");
		ModelAndView modelAndView = new ModelAndView();
		Student student = studentService.getStudent(id);
		System.out.println(student.getState_code());
		List<Country> countries = countryService.getCountries();
		List<State> states=stateService.getStatesByCountry_code(student.getCountry_code());
		List<District> districts=districtService.getDistrictsByState(student.getState_code());
		modelAndView.addObject("countries", countries);
		modelAndView.addObject("districts", districts);
		modelAndView.addObject("states",states);
		modelAndView.setViewName("studentForm");
		modelAndView.addObject("student", userStudentService.getUserStudentbyEmail(student.getEmail()));
		return modelAndView;
	}

	@PostMapping(value = "/saveStudent", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ModelAndView saveStudent(@ModelAttribute("student") UserStudent student,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(student.getPassword());
		modelAndView.addObject("student", student);
		System.out.println("state code " + student.getState_code() + " country code " + student.getCountry_code()
				+ " district code " + student.getDistrict_code());
		if (student.getSt_id() <= 0 || student == null || student.getUser_id() == "") {
			student.getState_code();
			try {
				if (student.getFirst_name() == "" || student.getLast_name() == "" || student.getPhone_no() == null
						|| student.getEmail() == "" || student.getAddress() == ""
						|| (student.getCountry_code() == "0" || student.getCountry_code() == null)
						|| (student.getState_code() == "0" || student.getState_code() == null)
						|| (student.getDistrict_code() == "0" || student.getDistrict_code() == null)) {
					modelAndView.setViewName("redirect:/addstudent?emptyFields=true");
				} else {
					System.out.println("adding student " + file);
//					student.setProfile_picture(student.getProFile().getBytes());
					if (!file.isEmpty())
						student.setProfile_picture(file.getBytes());
					studentService.addStudent(student);
					modelAndView.setViewName("redirect:/addstudent?success=true");
				}

			} catch (DuplicateKeyException e) {
				// TODO: handle exception
				User user = userService.getUserByEmail(student.getEmail());
				Student student2 = studentService.findStudentByEmail(student.getEmail());
				if (user != null) {
					userService.deleteUserByEmail(student.getEmail());
				}
				if (student2 != null) {
					studentService.deleteStudent(student2);
				}
				System.out.println(e.getCause());
				String redirectString = "redirect:/addstudent?";
				if (userStudentService.getUserStudentbyEmail(student.getEmail()) != null)
					redirectString += "emailAlreadyExists=true&";
				if (studentService.findStudentByPhoneNo(student.getPhone_no()) != null)
					redirectString += "phoneNoAlreadyExists=true";
				modelAndView.setViewName(redirectString);

			} catch (Exception e) {
				e.printStackTrace();
				User user = userService.getUserByEmail(student.getEmail());
				Student student2 = studentService.findStudentByEmail(student.getEmail());
				if (user != null) {
					userService.deleteUserByEmail(student.getEmail());
				}
				if (student2 != null) {
					studentService.deleteStudent(student2);
				}
			}

		}

		else {
			try {
//				student.setProfile_picture(student.getProFile().getBytes());
				System.out.println("Multipart File" + file.getBytes()+" "+file.isEmpty());
				if (!file.isEmpty())
					student.setProfile_picture(file.getBytes());
				System.out.println("student profile picture controller " + student.getProfile_picture());
				studentService.updateStudent(student);
				modelAndView.addObject("student", userStudentService.getUserStudentbyEmail(student.getEmail()));

				modelAndView.setViewName("redirect:/updatestudent/" + student.getSt_id() + "?updateSuccess=true");

//				student.setProfile_picture(file.getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}

		}
		return modelAndView;

	}

	@GetMapping(value = "/deleteStudent/{id}")
	public ModelAndView deleteStudent(@PathVariable("id") long id) {
		Student student = studentService.getStudent(id);
		ModelAndView modelAndView = new ModelAndView();
		studentService.deleteStudent(student);
		modelAndView.setViewName("redirect:/listStudent/1?userDeleteSuccess=true");

		return modelAndView;

	}

	@GetMapping(value = "/changePassword/{email}")
	public ModelAndView changePassword(@PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		UpdatePassword password = new UpdatePassword();
		password.setEmail(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", password);
		modelAndView.setViewName("changePassword");

		return modelAndView;

	}

	@PostMapping(value = "/updatePassword")
	public ModelAndView updatePassword(@ModelAttribute UpdatePassword updatePassword) {
		userService.updateUserPassword(updatePassword);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/changePassword/" + updatePassword.getEmail() + "?passwordUpdated=true");

		return modelAndView;

	}

	@GetMapping("/viewStudent/{id}")
	public ModelAndView viewStudent(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView("viewStudent");
		modelAndView.addObject("student", studentService.getAllStudentData(id));
		return modelAndView;

	}

}
