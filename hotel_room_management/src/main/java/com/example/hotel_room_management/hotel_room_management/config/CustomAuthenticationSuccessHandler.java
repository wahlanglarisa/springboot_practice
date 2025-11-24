package com.example.hotel_room_management.hotel_room_management.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//configure redirects
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("In authentication function");
		String redirectURL = request.getContextPath();

		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Guest"))) {
			redirectURL = "/student/studentHomepage";
		}
	
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			redirectURL ="/admin/adminPortal/1?sortField=email&sortDir=asc&role=";
		}
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Front Desk"))) {
			redirectURL ="/frontDesk";
		}
		;
		
		// TODO Auto-generated method stub
		response.sendRedirect(redirectURL);
	}

}
