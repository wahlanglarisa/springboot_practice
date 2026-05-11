package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.GetStudentClass;

@Repository
public class ClassCourseDaoImpl implements ClassCourseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<GetStudentClass> getStudentClasses(Long stID) {
		String dayString=LocalDateTime.now().getDayOfWeek().toString();
		System.out.println(dayString);
		String getStClass="select c.course_name courseName,cd.name dayName,"
				+ "concat(p.first_name,' ',p.last_name) profName,ct.start_time,ct.end_time"
				+ " from course_class cc "
				+ "join course c on cc.course_id=c.id"
				+ " join class_day cd on cd.id=cc.day_id"
				+ " join class_time ct on ct.id=cc.time_id"
				+ " join professor p on p.id=cc.professor_id"
				+ " join course_student sc on sc.course_id=c.id"
				+ " where sc.st_id=? and lower(cd.name)=?";
		
		// TODO Auto-generated method stub
		return jdbcTemplate.query(getStClass, ps->{ps.setLong(1,stID);ps.setString(2, dayString.toLowerCase());},new StudentClassMapper());
	}
	private static final class StudentClassMapper implements RowMapper{

		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new GetStudentClass(rs.getString("courseName"),rs.getString("dayName"),rs.getString("profName"),rs.getTime("start_time").toLocalTime(),rs.getTime("end_time").toLocalTime());
		}
		
	}

}
