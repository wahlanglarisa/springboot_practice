package com.example.student.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.model.UserDto;
import com.example.student.service.CourseService;
import com.example.student.service.StudentService;

@Controller
public class RegisterController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
  @GetMapping("/registerStudent")
  public String registerStudentPage(Model model) {
	  Collection<String> courseList=courseService.findCourses();
	  UserDto userDto=new UserDto();
	  userDto.getCourses().add("");
	  model.addAttribute("user", new UserDto());
	  model.addAttribute("courseList",courseList);
	  System.out.println("Hello"+courseList.toString());
	  return "registerStudent";
  }
  
  @PostMapping("/studentData")
  public String GetStudentData(@ModelAttribute("user") UserDto user) {
	  System.out.println("From /studentData "+user.getCourses());
	  studentService.saveStudent(user);
	  return "redirect:/registerStudent?success";
  }
 
}
