package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.ClassDay;
@Repository
public class ClassDayDaoImpl implements ClassDayDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<ClassDay> getClassDays() {
		String getClassString="select * from class_day";
		// TODO Auto-generated method stub
		return (List<ClassDay>)jdbcTemplate.query(getClassString, new ClassDayMapper());
	}
	private static final class ClassDayMapper implements RowMapper{

		@Override
		public @Nullable ClassDay mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new ClassDay(rs.getString("name"),(UUID) rs.getObject("id"));
		}
		
	}

}
