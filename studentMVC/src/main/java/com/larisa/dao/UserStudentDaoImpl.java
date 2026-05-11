package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.UserStudent;

@Repository
public class UserStudentDaoImpl implements UserStudentDao {
	@Override
	public UserStudent getUserStudentbyEmail(String email) {
		// TODO Auto-generated method stub
		String queryString = "select * from student join \"user\" "
				+ "on student.user_id=\"user\".user_id join user_creation_status"
				+ " on\"user\".creation_status_id=user_creation_status.id  "
				+ "left join student_address on st_id=student.id where student.email=?";
		List<UserStudent> userStudents = jdbcTemplate.query(queryString, ps -> {
			ps.setString(1, email);
		}, new UserStudentMapper());
		return userStudents.size() == 0 ? null : userStudents.getFirst();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserStudent getUserStudent(long id) {
		String queryString = "select * from \"user\" join student st on user.user_id=st.user_id join user_creation_status\"\r\n"
				+ "				+ \" on\\\"user\\\".creation_status_id=id  \"\r\n" + "				+ where st.id =?";
		List<UserStudent> userStudents = (List<UserStudent>) (jdbcTemplate.query(queryString, ps -> {
			ps.setLong(1, id);
		}, new UserStudentMapper()));
		// TODO Auto-generated method stub
		return (userStudents.size() == 0 ? null : userStudents.getFirst());

	}

	private final static class UserStudentMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub)
			UserStudent userStudent = new UserStudent(rs.getLong("phone_no"), rs.getLong("id"),
					rs.getString("password"), rs.getString("email"), rs.getString("last_name"),
					rs.getString("first_name"), rs.getString("user_id"), rs.getBytes("profile_picture"),
					(UUID) rs.getObject("creation_status_id"), rs.getString("address_perm_1"),
					rs.getString("address_perm_2"), rs.getString("address_perm_3"), rs.getString("address_present_1"),
					rs.getString("address_present_2"), rs.getString("address_present_3"),
					rs.getLong("address_perm_pin"), rs.getLong("address_present_pin"));
			userStudent.setUser_creation_status(rs.getString("status"));
			userStudent.setPermCountryCode(rs.getString("perm_country_code"));
			userStudent.setPermStateCode(rs.getString("perm_state_code"));
			userStudent.setPermDistrictCode(rs.getString("perm_district_code"));
			userStudent.setPreCountryCode(rs.getString("present_country_code"));
			userStudent.setPreStateCode(rs.getString("pre_state_code"));
			userStudent.setPreDistrictCode(rs.getString("pre_district_code"));
			return userStudent;
		}

	}
}
