package com.larisa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
	@Autowired
	UserDetailsService detailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()) .authorizeHttpRequests(auth -> auth

	            // ✅ ALLOW login page & authentication
	            .requestMatchers("/**", "/login", "/?error", "/?logout").permitAll()

	            // ✅ Static resources
	            .requestMatchers("/resources/**", "/css/**", "/js/**", "/images/**","/**.js/").permitAll()

	            // ✅ Role-based access
	            .requestMatchers("/liststudent**").hasRole("Admin")
	            .requestMatchers("/student/**").hasRole("Student")

	            // ✅ Everything else must be logged in
	            .anyRequest().authenticated()
	        )  .formLogin(form -> form
	                .loginPage("/")                     // your login JSP
	                .loginProcessingUrl("/login")       // VERY IMPORTANT
	               .successHandler(authenticationSuccessHandler())
	                .failureUrl("/?loginerror=true")
	                .permitAll()
	            )

	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/?loggedOut=true")
	            );
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(detailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		System.out.println("Inside AuthenticationProvider Function");
		return provider;
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		System.out.println("Authentication success handler");
		return new CustomSuccessHandler();
	}

}
