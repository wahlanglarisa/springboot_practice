package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.larisa.dto.UpdatePassword;
import com.larisa.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public boolean validatePassword(String email, String oldPassword) {
		String queryString = "select * from \"user\" where email = ?";
		User user = (User) (jdbcTemplate.query(queryString, ps -> {
			ps.setString(1, email);
		}, new UserMapper())).getFirst();
		// TODO Auto-generated method stub
		if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			return true;
		} else {
			return false;
		}

	}

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RoleDao roleDao;
	@Override
	public User updateUser(User user) {
		String queryString = "update \"user\" set email=?" + " where user_id=?";

		// TODO Auto-generated method stub
		System.out.println("User update "+user.getEmail()+" "+user.getUserid());
		jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getUserid());

			};
		});
		String getString = "select * from \"user\" where email=?";
		// TODO Auto-generated method stub
		List<User> users = (List<User>) (jdbcTemplate.query(getString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getEmail());
			}
		}, new UserMapper()));
		return (users.size() == 0 ? null : users.getFirst());
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public User saveUser(User user) throws DuplicateKeyException {
		String queryString = "insert into \"user\"(email,password,role_id) values(?,?,?)";
		// TODO Auto-generated method stub
System.out.println("User Dao "+user.getPassword());
		jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getEmail());
				ps.setString(2, bCryptPasswordEncoder.encode(user.getPassword()));
				ps.setObject(3, roleDao.getRoleByName("Student").getRoleId());
			};
		});
		String getString = "select * from \"user\" where email=?";
		// TODO Auto-generated method stub
		List<User> users = (List<User>) (jdbcTemplate.query(getString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getEmail());
			}
		}, new UserMapper()));
		return (users.size() == 0 ? null : users.getFirst());

	}

	@Override
	public UserRole validateUser(User user) {
		String queryString = "select * from \"user\" join \"role\" on \"user\".role_id=\"role\".id where \"user\".email=?";
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<UserRole> users = (List<UserRole>) (jdbcTemplate.query(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getEmail());
			}
		}, new UserRoleMapper()));
		System.out.println(users.size());
		if (users.size() == 0) {
			return null;
		} else if (bCryptPasswordEncoder.matches(user.getPassword(), users.getFirst().getPassword())) {
			return users.getFirst();

		} else {
			return null;
		}
	}

	@Override
	public String deleteUser(String userId) {
		// TODO Auto-generated method stub
		String queryString = "delete from \"user\" where user_id=?";
		int linesAffected = jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
				ps.setString(1, userId);
			};
		});
		return (linesAffected > 0 ? "Data deleted successfully" : " ");

	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		String getString = "select * from \"user\" where email=?";
		// TODO Auto-generated method stub
		List<User> users = (List<User>) (jdbcTemplate.query(getString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
			}
		}, new UserMapper()));
		return (users.size() == 0 ? null : users.getFirst());

	}

	@Override
	public String deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		String queryString = "delete from \"user\" where email=?";
		int linesAffected = jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
				ps.setString(1, email);
			};
		});
		return (linesAffected > 0 ? "Data deleted successfully" : " ");
	}

	@Override
	public User updateUserPassword(UpdatePassword password) {
		String queryString = "update \"user\" set password=? where email=?";
		// TODO Auto-generated method stub
		int linesAffected = jdbcTemplate.update(queryString,
				new Object[] { bCryptPasswordEncoder.encode(password.getNewPassword()), password.getEmail() });
		String getUserQueryString = "select * from \"user\" where email=? ";
		List<User> user = (jdbcTemplate.query(getUserQueryString, ps -> {
			ps.setString(1, password.getEmail());
		}, new UserMapper()));
		return (linesAffected > 0 ? user.getFirst() : null);
	}

	private final static class UserMapper implements RowMapper {

		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User(rs.getString("password"), rs.getString("email"));
			user.setUserid(rs.getString("user_id"));
			user.setRoleid(rs.getString("role_id"));

			return user;
		}

	}

	private final static class UserRoleMapper implements RowMapper {

		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			UserRole userRole = new UserRole(rs.getString("email"), rs.getString("password"), rs.getString("user_id"),
					rs.getString("name"), rs.getString("role_id"));
			
			return userRole;
		}

	}
}
