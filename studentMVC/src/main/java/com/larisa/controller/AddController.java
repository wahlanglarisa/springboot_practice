package com.larisa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.larisa.service.AddService;

@Controller

public class AddController {
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("n1") int num1, @RequestParam("n2") int num2) {
		
		AddService addService=new AddService();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("Display");
		modelAndView.addObject("sum", addService.add(num1, num2));
		return modelAndView;
	}
	
}
