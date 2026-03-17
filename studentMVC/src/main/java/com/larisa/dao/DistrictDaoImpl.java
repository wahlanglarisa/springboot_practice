package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.District;
@Repository
public class DistrictDaoImpl implements DistrictDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<District> getDistrictsByState(String stateCode) {
		// TODO Auto-generated method stub
		String queryString = "select * from district where district.state_code=?";
		return jdbcTemplate.query(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, stateCode);
			}
			;
		}, new DistrictMapper());
	}

	private static final class DistrictMapper implements RowMapper{
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new District(rs.getString("district_code"), rs.getString("state_code"), rs.getString("district_name"));
		}
	}
}
