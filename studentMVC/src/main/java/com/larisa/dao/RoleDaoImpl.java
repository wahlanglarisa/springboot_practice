package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.Role;
@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return (Role)jdbcTemplate.query("select * from \"role\" where name=?", ps -> {
			ps.setString(1, name);
		}, new RoleMapper()).getFirst();
	}

	private static final  class RoleMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Role role = new Role(rs.getString("name"),(UUID)rs.getObject("id"));
			return role;
		}

	}
}
