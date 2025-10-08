package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.Course;
import com.example.student.service.CourseService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AJAXController {
    	@Autowired
	private CourseService courseService;
	@RequestMapping(value="/admin/"
			+ "getDepartmentAJAX/",method = RequestMethod.GET)
	public List<String> getDepartmentAJAX(HttpServletRequest httpRequest,@RequestParam("id") long id) {
        System.out.println(id);
	List<String> courses=courseService.findbyDepartmentID(id);
    System.out.println(courses);
		return courses;
	}
}

