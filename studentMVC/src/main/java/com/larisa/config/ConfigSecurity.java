package com.larisa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
	@Autowired
	UserDetailsService detailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http.authorizeHttpRequests(auth -> auth

				// login page
				.requestMatchers("/", "/login", "/addstudent**", "/getState_codes**", "/getDistrict_codes**",
						"/getUser**", "/sendMail**", "/getStudent**", "/validatePassword**", "/image**",
						"/getCourseByStudent**", "/verifyOTP**", "/verifyPhoneOTP**", "/sendSMSOTP**",
						"/getStudentAddress**", "/saveStudent", "/verifyOTP/","/setSession")
				.permitAll()

				// static resources
				.requestMatchers("/resources/**", "/css/**", "/js/**", "/images/**").permitAll()

				// role access
				.requestMatchers("/admin**", "/liststudent**", "/updatestudent**", "/changePassword**").hasRole("Admin")
				.requestMatchers("/student**", "/updatestudent**", "/changePassword**").hasRole("Student")
				.requestMatchers("/professor**", "/updatestudent**", "/changePassword**").hasRole("Professor")

				// everything else requires login
				.anyRequest().authenticated())

				.formLogin(form -> form
						.loginPage("/") // login page
						.successHandler(authenticationSuccessHandler()).loginProcessingUrl("/login")

						.failureUrl("/?loginerror=true")
						.permitAll())

				.logout(logout -> logout
						.logoutUrl("/logout") // POST /logout handled by Spring Security
						.logoutSuccessUrl("/?loggedOut=true")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll())

				.sessionManagement(session -> session
						.invalidSessionUrl("/")
						.maximumSessions(1)
						.maxSessionsPreventsLogin(false))

				.headers(headers -> headers.cacheControl(cache -> {
				}));

		return http.build();
	}

	@Bean
	@Primary
	public RSAPasswordEncoder RSApasswordEncoder() {
		return new RSAPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(detailsService);
		provider.setPasswordEncoder(RSApasswordEncoder());
		System.out.println("Inside AuthenticationProvider Function "+RSApasswordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		System.out.println("Authentication success handler");

		return new CustomSuccessHandler();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
}
