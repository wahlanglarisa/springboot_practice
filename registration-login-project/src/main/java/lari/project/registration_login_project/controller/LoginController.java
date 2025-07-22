package lari.project.registration_login_project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lari.project.registration_login_project.Service.UserService;
import lari.project.registration_login_project.model.User;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@GetMapping("/login")
	public String showRegistrationForm(HttpServletRequest request, Model model) {
		model.addAttribute("id", request.getSession().getId());
		return "login";
	}

	@GetMapping("/")
	public String home(HttpServletRequest request, Model model) {
		Principal principal = request.getUserPrincipal();
		User user=userService.findByEmail(principal.getName());
		System.out.println(user.getEmail()+" "+ user.getFirstName()+" "+user.getLastName());
		model.addAttribute("user",user);
		return "index";
	}
}
