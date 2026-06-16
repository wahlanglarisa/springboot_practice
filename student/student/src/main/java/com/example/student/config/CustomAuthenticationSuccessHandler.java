package com.example.student.config;

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
		String contextPath=redirectURL;
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Student"))) {
			redirectURL = contextPath+"/student/studentHomepage";
		}
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Professor"))) {
			redirectURL = contextPath+"/professor/professorHomepage";
		}
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			redirectURL =contextPath+"/admin/adminPortal/1?sortField=email&sortDir=asc&role=";
		}
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Head Of Department"))) {
			redirectURL =contextPath+"/hod/hodPortal";
		}
		;
		
		// TODO Auto-generated method stub
		response.sendRedirect(redirectURL);
	}

}
