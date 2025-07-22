package lari.project.registration_login_project.Service;

import lari.project.registration_login_project.dto.UserRegistrationDto;
import lari.project.registration_login_project.model.User;

public interface UserService {
	public User save(UserRegistrationDto user);
	public User findByEmail(String email);
}
