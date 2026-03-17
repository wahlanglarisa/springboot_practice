package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		String queryString = "select * from student join \"user\" " + "on student.user_id=\"user\".user_id "
				+ "where student.email=?";
		List<UserStudent> userStudents = jdbcTemplate.query(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
			};
		}, new UserStudentMapper());
		return userStudents.size()==0?null:userStudents.getFirst();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserStudent getUserStudent(long id) {
		String queryString = "select * from \"user\" join student st on user.user_id=st.user_id where st.id =?";
		List<UserStudent> userStudents = (List<UserStudent>) (jdbcTemplate.query(queryString,
				new PreparedStatementSetter() {
					public void setValues(java.sql.PreparedStatement ps) throws SQLException {
						ps.setLong(1, id);
					};
				}, new UserStudentMapper()));
		// TODO Auto-generated method stub
		return (userStudents.size() == 0 ? null : userStudents.getFirst());

	}

	private final static class UserStudentMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub)
			UserStudent userStudent = new UserStudent(rs.getLong("phone_no"), rs.getLong("id"),
					rs.getString("password"), rs.getString("email"), rs.getString("district_code"),
					rs.getString("state_code"), rs.getString("country_code"), rs.getString("last_name"),
					rs.getString("first_name"), rs.getString("user_id"), rs.getString("address"),rs.getBytes("profile_picture"));

			return userStudent;
		}

	}
}
