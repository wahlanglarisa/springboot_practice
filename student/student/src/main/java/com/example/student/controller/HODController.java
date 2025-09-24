package com.example.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.model.Branch;
import com.example.student.model.Class_Course;
import com.example.student.model.Course;
import com.example.student.model.Professor;
import com.example.student.model.Student;
import com.example.student.model.wrapper.AddStudentClass;
import com.example.student.model.wrapper.FindProfessorClasses;
import com.example.student.model.wrapper.ProfListClasses;
import com.example.student.model.wrapper.SaveStudentClass;
import com.example.student.service.BranchService;
import com.example.student.service.ClassService;
import com.example.student.service.CourseService;
import com.example.student.service.ProfessorService;
import com.example.student.service.StudentClassService;
import com.example.student.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HODController {
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassService classService;
	@Autowired
	private StudentClassService studentClassService;
	@Autowired
	private BranchService branchService;

	@GetMapping("/hod/hodPortal")
	public String profHomepage(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		List<FindProfessorClasses> professorClasses = professorService.findProfessorClasses(principal.getName());
		List<ProfListClasses> profListClasses = professorService.getClass_Courses(principal.getName());
		Professor professor = professorService.getProfByEmail(principal.getName());
		List<Student> students = studentService.getStudentByDepartment(professor.getDepartment().getId());
		System.out.println(students + " " + professor.getDepartment().getId());
		List<Professor> professors = professorService.findByDepartmentID(professor.getDepartment().getId());
		System.out.println(professors);
		model.addAttribute("students", students);
		model.addAttribute("user", principal.getName());
		model.addAttribute("professors", professors);
		model.addAttribute("classCount", professorClasses);
		model.addAttribute("routines", profListClasses);

		return "hodPortal";

	}

	@GetMapping("/hod/viewClass")
	public String viewClass(Model model, HttpServletRequest httpServletRequest) {
		Principal principal = httpServletRequest.getUserPrincipal();
		// List<FindProfessorClasses> professorClasses =
		// professorService.findProfessorClasses(principal.getName());
		// model.addAttribute("user", principal.getName());
		// List<ProfListClasses> profListClasses =
		// professorService.getClass_Courses(principal.getName());
		// model.addAttribute("classCount", professorClasses);
		// model.addAttribute("routines", profListClasses);
		Professor professor = professorService.getProfByEmail(principal.getName());
		List<ProfListClasses> profListClasses = professorService
				.getDeptClass_Courses(professor.getDepartment().getId());

		System.out.println("Department ID: " + professor.getDepartment().getId() + "List: " + profListClasses);
		model.addAttribute("routines", profListClasses);
		return "viewClassList";

	}

	@GetMapping("/hod/addNewClassPage")
	public String addClassPage(Model model, HttpServletRequest httpServletRequest) {
		String email = httpServletRequest.getUserPrincipal().getName();
		Professor professor = professorService.getProfByEmail(email);
		List<Professor> professors = professorService.findByDepartmentID(professor.getDepartment().getId());
		List<Course> courses = courseService.findbyDepartment(professor.getDepartment());
		System.out.println(courses);
			List<Branch> branches=branchService.findBranchByDepartment(professorService.getProfByEmail(email).getDepartment());
		model.addAttribute("branches", branches);
		model.addAttribute("courses", courses);
		model.addAttribute("professors", professors);
		model.addAttribute("class", new Class_Course());
		return "addNewClass";
	}

	@PostMapping("/hod/saveClass")
	public String saveClass(@ModelAttribute("class") Class_Course class_Course, HttpServletRequest httpServletRequest) {
		System.out.println("Course Name " + class_Course.getCourse_class().getCourseName() + " Course ID "
				+ class_Course.getCourse_class().getId());
		System.out
				.println(class_Course.getProfessor().getFirstName() + " " + class_Course.getProfessor().getLastName());
		classService.savClass_Course(class_Course);
		return "redirect:/hod/addNewClassPage";
	}

	@GetMapping("/hod/updateClassPage/{id}")
	public String updateClassPage(HttpServletRequest httpServletRequest, @PathVariable("id") long id, Model model) {
		Class_Course class_Course = classService.findById(id);
		System.out.println(class_Course);
		String email = httpServletRequest.getUserPrincipal().getName();
		List<Course> courses = courseService.findbyDepartment(professorService.getProfByEmail(email).getDepartment());
		List<Professor> professors = professorService
				.findByDepartmentID(professorService.getProfByEmail(email).getDepartment().getId());
		List<Branch> branches=branchService.findBranchByDepartment(professorService.getProfByEmail(email).getDepartment());
		model.addAttribute("branches", branches);
		model.addAttribute("class", class_Course);
		model.addAttribute("professors", professors);
		return "updateClass";
	}

	@PostMapping("/hod/updateClass")
	public String updateClass(@ModelAttribute("class") Class_Course class_Course) {
		classService.savClass_Course(class_Course);
		return "redirect:/hod/viewClass";
	}

	@GetMapping("/hod/deleteClass/{id}")
	public String deleteClass(@ModelAttribute("class") Class_Course class_Course, @PathVariable("id") long id) {
		classService.deleteClassById(id);
		return "redirect:/hod/viewClass";
	}

	@GetMapping("/hod/viewStudentsPage")
	public String viewStudentPage(HttpServletRequest httpServletRequest, Model model) {
		Principal principal = httpServletRequest.getUserPrincipal();

		Professor professor = professorService.getProfByEmail(principal.getName());
		List<Student> students = studentService.getStudentByDepartment(professor.getDepartment().getId());
		System.out.println(students + " " + professor.getDepartment().getId());
		model.addAttribute("students", students);
		return "viewStudents";
	}

	@GetMapping("/hod/assignClassPage/{studentID}/{branch}/{semester}")
	public String assignClassPage(Model model, @PathVariable("branch") Long branch,
			@PathVariable("semester") Long semester, @PathVariable("studentID") Long studentID) {
		List<Class_Course> class_Courses = classService.findByBranchIDAndSemester(branch, semester);
		model.addAttribute("classes", class_Courses);
		model.addAttribute("studentID", studentID);
		model.addAttribute("saveClass", new SaveStudentClass());
		System.out.println(class_Courses);
		return "assignClass";
	}

	@PostMapping("/hod/saveStudenClass/")
	public String saveStudentClass(@ModelAttribute("saveClass") SaveStudentClass addStudentClass) {
		// TODO: process POST request
		System.out.println(addStudentClass.getAddStudentClasses());
		for (AddStudentClass addStudentClass2 : addStudentClass.getAddStudentClasses()) {
			System.out.println(addStudentClass2.isChecked());
		}
		studentClassService.savStudentClass(addStudentClass);
		return "redirect:/hod/hodPortal";
	}

}