package com.example.student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
                .csrf().disable() // CSRF protection is disabled for simplicity, reconsider enabling it in production
                .authorizeHttpRequests(authorize -> authorize.requestMatchers( "/registerStudent/**","/css/**", "**.js", "/images/**","/html/**","/studentData/**").permitAll()
                        .anyRequest().authenticated()                  // All other requests require authentication
                )
                .formLogin(form->form.loginPage("/login").permitAll()// Allow anyone to access the login page
                                                    // Allow logout without restriction
                ).logout(logout -> logout
                        .permitAll()                                     // Allow logout without restriction
                ).build();
	
	}
//	@Bean 
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
////		provider.setPasswordEncoder();
//		System.out.println("Inside AuthenticationProvider Function");
//		provider.setUserDetailsService(userDetailsService);
//		return provider; 
//	}
}

