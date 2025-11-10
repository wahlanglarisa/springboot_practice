package com.example.student.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.StudentClass;
import com.example.student.model.Test;
import com.example.student.model.TestStudent;
import com.example.student.model.wrapper.AttendancePage;
import com.example.student.model.wrapper.FindProfessorClasses;
import com.example.student.model.wrapper.ProfListClasses;
import com.example.student.model.wrapper.StudentsTestData;
import com.example.student.model.wrapper.UpdateTestStudent;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;
import com.example.student.service.TestService;
import com.example.student.service.TestStudentService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TestService testService;
	@Autowired
	private TestStudentService testStudentService;

	@GetMapping("/professor/professorHomepage")
	public String profHomepage(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		List<FindProfessorClasses> professorClasses = professorService.findProfessorClasses(principal.getName());
		List<ProfListClasses> profListClasses = professorService.getClass_Courses(principal.getName());
		Professor professor = professorService.getProfByEmail(principal.getName());

		List<Test> tests = testService.findByProfessor(professor);
		model.addAttribute("user", principal.getName());
		model.addAttribute("classCount", professorClasses);
		model.addAttribute("tests", tests);
		model.addAttribute("professor", professor);
		model.addAttribute("routines", profListClasses);
		model.addAttribute("date", LocalDateTime.now());

		return "professorHomepage";

	}

	@GetMapping("/professor/attendancePage/{id}")
	private String attendancePage(Model model, HttpServletRequest httpServletRequest, @PathVariable("id") long id) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		Course course=courseService.findById(id);
		List<AttendancePage> attendancePages = professorService.getAttendancePages(email, id);
		model.addAttribute("course", courseService.findById(attendancePages.getFirst().getCourseID()));
		System.out.println(attendancePages.getFirst().getClass_id());
		model.addAttribute("user", email);

		model.addAttribute("students", attendancePages);
		model.addAttribute("class", new com.example.student.model.wrapper.saveAttendance());
		System.out.println(courseService.findById(attendancePages.getFirst().getCourseID()));
		System.out.println(id + " " + attendancePages);
		return "attendancePage";

	}

	@PostMapping("/professor/saveAttendance/")
	private String saveAttendance(
			@ModelAttribute("class") com.example.student.model.wrapper.saveAttendance studentClass,
			Authentication authentication, HttpServletRequest request) {
		System.out.println(studentClass.getStudentClasses().getFirst().getClass_Course().getId());
		String str = professorService.saveAttendance(studentClass);
		System.out.println(str);
		String redirectURL = request.getContextPath();

		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Head Of Department"))) {
			return "redirect:/hod/hodPortal";
		}
		return "redirect:/professor/professorHomepage";
	}

	@GetMapping("/professor/createTestPage/{courseID}/{profID}/{classID}")
	private String createTestPage(Principal principal, Model model, @PathVariable("courseID") long courseID,
			@PathVariable("profID") long profID, @PathVariable("classID") long classID) {
		model.addAttribute("profID", profID);
		model.addAttribute("courseID", courseID);
		model.addAttribute("classID", classID);
		model.addAttribute("test", new Test());
		Professor professor = professorService.getProfByEmail(principal.getName());
		model.addAttribute("professor", professor);
		return "createTestPagewithCourse";
	}

	@GetMapping("/professor/createTestPage/{profID}")
	private String createTestPage(Model model, @PathVariable("profID") long profID, Principal principal) {
		model.addAttribute("profID", profID);
		model.addAttribute("test", new Test());
		Professor professor = professorService.getProfByEmail(principal.getName());
		model.addAttribute("professor", professor);
		List<Course> courses = courseService.findByProfessor(professor.getID());
		System.out.println(courses);
		model.addAttribute("courses", courses);
		return "createTestPage";
	}

	@PostMapping("/professor/saveTest/")
	private String saveTest(Model model, Test test) {
		System.out.println(test.getCourse().getCourseName() + " " + test.getProfessor().getFirstName());
		testService.createtest(test);
		return "redirect:/professor/professorHomepage";
	}

	@PostMapping("/professor/saveTestStudent/")
	private String saveTestStudent(@ModelAttribute("testMarks") UpdateTestStudent testStudent) {
		System.out.println(testStudent.getTestStudents());
		for (TestStudent tStudent : testStudent.getTestStudents()) {
			System.out.println(tStudent.getTestStudentID() + " " + tStudent.getMarks() + " " + tStudent.getStudent()
					+ " " + tStudent.getTest());
		}
		testStudentService.saveTestStudent(testStudent.getTestStudents());
		return "redirect:/professor/professorHomepage";
	}

	@GetMapping("/professor/viewAllClasses")
	public String viewProfClasses(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		List<ProfListClasses> profListClasses = professorService.getProfClass_Courses(email);
		Professor professor = professorService.getProfByEmail(principal.getName());

		model.addAttribute("routines", profListClasses);
		model.addAttribute("professor", professor);
		return "viewClassesProf";
	}

	@GetMapping("/professor/addMarksStudents/{testID}")
	public String getMethodName(Model model, @PathVariable("testID") long id) {
		System.out.println(testStudentService.getStudentByTestID(id));
		List<StudentsTestData> studentsTestData = testStudentService.getStudentByTestID(id);
		model.addAttribute("testMarks", new UpdateTestStudent());
		model.addAttribute("testStudents", studentsTestData);
		return "AddStudentMarks";
	}

	@GetMapping("/professor/viewRoutine/{day}")
	public String viewRoutinePage(Model model, @PathVariable("day") String day, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		String email = principal.getName();
		List<ProfListClasses> profListClasses = professorService.getProfClass_Courses_By_Day(email, day);
		System.out.println(profListClasses);
		model.addAttribute("routine", profListClasses);
		model.addAttribute("day", day);
		return "viewRoutine";
	}

}
