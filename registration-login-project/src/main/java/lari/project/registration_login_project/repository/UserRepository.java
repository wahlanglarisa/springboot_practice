package lari.project.registration_login_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lari.project.registration_login_project.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 public User findByEmail(String userName);
}
