package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.ClassTime;

@Repository
public class ClassTimeDaoImpl implements ClassTimeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ClassTime> getAvailableClassTimes(String day) {
		String getAvailClassTimeQueryString = "select ct.start_time,ct.end_time,ct.id " + "from class_time ct "
				+ "where ct.id not in"
				+ "(select cc.time_id from course_class cc join class_day cd on cd.id=cc.day_id where cd.name=?)";
		// TODO Auto-generated method stub
		return jdbcTemplate.query(getAvailClassTimeQueryString, ps->{ps.setString(1, day);}, new ClassTimeMapper());
	}

	private static final class ClassTimeMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new ClassTime((UUID) rs.getObject("id"), rs.getTime("start_time").toLocalTime(),
					rs.getTime("end_time").toLocalTime());
		}

	}
}
