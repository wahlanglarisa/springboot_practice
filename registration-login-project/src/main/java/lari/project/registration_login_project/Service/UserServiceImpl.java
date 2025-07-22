package lari.project.registration_login_project.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lari.project.registration_login_project.dto.UserRegistrationDto;
import lari.project.registration_login_project.model.Role;
import lari.project.registration_login_project.model.User;
import lari.project.registration_login_project.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Override
	public User save(UserRegistrationDto userReg) {
		User user=new User(userReg.getFirstName(),userReg.getLastName(),userReg.getEmail(), passwordEncoder.encode(userReg.getPassword()) , Arrays.asList(new Role("ROLE_USER"))); 
		System.out.println("Saved");
		return userRepository.save(user);
	}
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		
		return user;
	}

}
