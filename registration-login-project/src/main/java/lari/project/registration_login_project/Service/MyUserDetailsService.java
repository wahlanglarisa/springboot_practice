package lari.project.registration_login_project.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lari.project.registration_login_project.model.User;
import lari.project.registration_login_project.model.UserPrincipal;
import lari.project.registration_login_project.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user1=userRepository.findByEmail(username);
		System.out.println("In load by username function");
//		System.out.println(user1.getEmail()+" "+user1.getFirstName()+" "+user1.getLastName()+" "+user1.getPassword());
		return new UserPrincipal(user1);
	}
}
