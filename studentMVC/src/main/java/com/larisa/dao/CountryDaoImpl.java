package com.larisa.dao;

import java.security.PrivateKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.Country;

@Repository
public class CountryDaoImpl implements CountryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Country> getCountries() {
		String queryString="select * from country";
		
		// TODO Auto-generated method stub
		return (List<Country>) jdbcTemplate.query(queryString, new CountryMapper());
	}

	private static final class CountryMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Country country = new Country(rs.getString("country_code"), rs.getString("country_name"));
			return country;
		}
	}

}
