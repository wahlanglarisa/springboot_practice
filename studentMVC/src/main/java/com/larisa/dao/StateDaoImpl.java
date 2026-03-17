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

import com.larisa.dto.State;
@Repository
public class StateDaoImpl implements StateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<State> getStatesByCountry_code(String countryCode) {
		String queryString = "select * from state where state.country_code=?";
		return jdbcTemplate.query(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
				ps.setString(1, countryCode);
			};
		}, new StateMapper());
		// TODO Auto-generated method stub
		
	}

	private static final class StateMapper implements RowMapper{@Override
	public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		State state=new State(rs.getString("country_code"), rs.getString("state_name"), rs.getString("state_code"));
		return state;

	}
		// TODO Auto-generated method stub
	}

}
