package com.larisa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.Course;
import com.larisa.dto.Student;

@Repository
public class CourseDaoImpl implements CourseDao {
	@Override
	public Page<Course> getCoursesByStudent(Student student, Pageable pageable) {
		String rowStringQuery = "select count(*) from course_student cs where cs.st_id=?";
		int totalRows = jdbcTemplate.queryForObject(rowStringQuery, new Object[] { student.getId() }, Integer.class);
		// TODO Auto-generated method stub
		String qString = "select c.semester,c.is_elective,c.credit,c.course_name,c.id from student st join course_student cs "
				+ "on st.id=cs.st_id" + " join course c " + "on c.id=cs.course_id where st.id=? limit ? offset ?";

		List<Course> courses = jdbcTemplate.query(qString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, student.getId());
				ps.setInt(2, pageable.getPageSize());
				ps.setLong(3, pageable.getOffset());
			};
		}, new CourseMapper());
		return new PageImpl<Course>(courses, pageable, totalRows);
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Course> getCoursesNotTakenByStudent(Student student) {
		// TODO Auto-generated method stub
		String queryString = "select * from course c where c.id not in "
				+ "(select cs.course_id from course_student cs where cs.st_id=?)";
		// TODO Auto-generated method stub
		return jdbcTemplate.query(queryString, ps -> {
			ps.setLong(1, student.getId());
		}, new CourseMapper());
	}

	@Override
	public List<Course> getCourses() {
		String queryString = "select * from course";
		// TODO Auto-generated method stub
		return jdbcTemplate.query(queryString, new CourseMapper());
	}

	public static class CourseMapper implements RowMapper<Course> {
		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Course course = new Course(rs.getBoolean("is_elective"), rs.getLong("semester"), rs.getLong("id"),
					rs.getLong("credit"), rs.getString("course_name"));
			return course;
		}
	}

}
