package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.UserCreationStatus;
@Repository
public class UserCreationStatusDaoImpl implements UserCreationStatusDao {
	@Override
	public UserCreationStatus getByStatusByID(UUID id) {
		// TODO Auto-generated method stub
		return (UserCreationStatus)(jdbcTemplate.query("select * from user_creation_status where id=?", (ps) -> {
			ps.setObject(1, id);
		}, new UserCreationStatusMapper())).getFirst();
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserCreationStatus getByStatusName(String statusName) {
		// TODO Auto-generated method stub
		return (UserCreationStatus)(jdbcTemplate.query("select * from user_creation_status where status_name=?", (ps) -> {
			ps.setString(1, statusName);
		}, new UserCreationStatusMapper())).getFirst();
	}

	private static final class UserCreationStatusMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new UserCreationStatus((UUID) rs.getObject("id"), rs.getString("status_name"), 
					rs.getString("status"));
		}
	}
}