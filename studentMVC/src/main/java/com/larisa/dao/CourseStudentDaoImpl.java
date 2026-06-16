package com.larisa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.CourseStudent;

@Repository
public class CourseStudentDaoImpl implements CourseStudentDao {
	@Override
	public String deleteCourseStudent(Long stID, Long courseID) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String queryString = "delete from course_student where course_id=? and st_id=?";
		int linesAffected = jdbcTemplate.update(queryString, ps -> {

			ps.setLong(1, courseID);
			ps.setLong(2, stID);

		});
		return (linesAffected > 0 ? "Data inserted successfully" : " ");
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String saveCourseStudent(Long stID, List<Long> courseID) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		String queryString = "insert into course_student(course_id,st_id) " + "values (?,?)";
		int linesAffected[][] = jdbcTemplate.batchUpdate(queryString, courseID, courseID.size(),
				(PreparedStatement ps, Long courseid) -> {

					System.out.println(courseid + " " + stID);
					ps.setLong(1, courseid);
					ps.setLong(2, stID);

				});
		System.out.println(linesAffected+" courses added ");
		return (linesAffected.length > 0 ? "Data inserted successfully" : " ");
	}

	@Override
	public String deleteCourseStudentByStID(Long stID) {
		// TODO Auto-generated method stub
		String queryString = "delete from course_student where st_id=?";
		int linesAffected = jdbcTemplate.update(queryString, ps -> {

			ps.setLong(1, stID);

		});
		return (linesAffected > 0 ? "Data inserted successfully" : " ");
	}

	private static final class CourseStudentMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			CourseStudent courseStudent = new CourseStudent(rs.getLong("course_id"), rs.getLong("st_id"));
			return null;
		}
	}
}
