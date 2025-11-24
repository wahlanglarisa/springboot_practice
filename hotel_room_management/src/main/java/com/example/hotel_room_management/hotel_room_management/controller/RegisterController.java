package com.example.hotel_room_management.hotel_room_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hotel_room_management.hotel_room_management.model.User;
import com.example.hotel_room_management.hotel_room_management.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin/registerEmployee")
    public String registerUser(Model model) {
        model.addAttribute("user",new User());
        return "registerEmployee";
    }
    @PostMapping("/save")
    public String saveUser(User user) {
        //TODO: process POST request
        try{
            System.out.println(user);
      userService.saveUser(user);
        return "redirect:/register?sucess";
        }catch(Exception e){
            return "redirect:/register?error";
        }
  
    }
    
    
}
