package lari.project.registration_login_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return  http
                .csrf().disable() // CSRF protection is disabled for simplicity, reconsider enabling it in production
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/registration/**", "/css/**", "**.js", "/images/**","/html/**").permitAll()
                        .anyRequest().authenticated()                  // All other requests require authentication
                )
                .formLogin(form->form.loginPage("/login").permitAll()// Allow anyone to access the login page
                                                    // Allow logout without restriction
                ).logout(logout -> logout
                        .permitAll()                                     // Allow logout without restriction
                ).build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails=User.withDefaultPasswordEncoder().username("larisa").password("12345678").build();
//		return new InMemoryUserDetailsManager();
//	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean 
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		System.out.println("Inside AuthenticationProvider Function");
		provider.setUserDetailsService(userDetailsService);
		return provider; 
	}
}
