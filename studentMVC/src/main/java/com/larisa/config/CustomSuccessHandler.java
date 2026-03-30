package com.larisa.config;


import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.larisa.dao.UserCreationStatusDao;
import com.larisa.dto.UserCreationStatus;
import com.larisa.dto.UserStudent;
import com.larisa.service.UserStudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//configure redirects
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private UserStudentService userStudentService;
	@Autowired
	private UserCreationStatusDao creationStatusDao;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("In authentication function");
		String redirectURL = request.getContextPath();
		System.out.println(redirectURL);
		String contextPath=redirectURL;
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Student"))) {
			System.out.println(authentication.getName());
			UserStudent userStudent=userStudentService.getUserStudentbyEmail(authentication.getName());

			UserCreationStatus creationStatus=creationStatusDao.getByStatusByID(userStudent.getCreationStatusID());

			redirectURL = contextPath+"/studentHomepage/" + authentication.getName();
		}
//		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Professor"))) {
//			redirectURL = contextPath+"/professor/professorHomepage";
//		}
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
			redirectURL =contextPath+"/adminHomepage/";
		}
//		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Head Of Department"))) {
//			redirectURL =contextPath+"/hod/hodPortal";
//		}
		;
		
		// TODO Auto-generated method stub
		response.sendRedirect(redirectURL);
	}

}
