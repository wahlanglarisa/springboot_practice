package com.example.hotel_room_management.hotel_room_management.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

     @GetMapping("/login")
    public String login(){
        System.out.println("in controller");
        return "login";
    }
}
 