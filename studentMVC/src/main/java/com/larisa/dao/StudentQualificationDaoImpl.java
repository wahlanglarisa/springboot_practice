package com.larisa.dao;

import java.awt.Taskbar.State;
import java.sql.PreparedStatement;
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

import com.larisa.dto.GetStudentQualDetails;
import com.larisa.dto.StudentQualification;

@Repository
public class StudentQualificationDaoImpl implements StudentQualificationDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addQualifications(List<StudentQualification> studentQualifications) {
		String addQualQuery = "INSERT INTO public.student_qualification(\r\n"
				+ "	qualification_stream, percentage, st_id, qualification_type)\r\n" + "	VALUES (?, ?, ?, ?)";
		System.out.println("Adding Qualifications");
		// TODO Auto-generated method stub
		jdbcTemplate.batchUpdate(addQualQuery, studentQualifications, studentQualifications.size(),
				(PreparedStatement ps, StudentQualification studentQualification) -> {
					System.out.println("Adding Qualifications");

					ps.setString(1, studentQualification.getQualificationStream());
					ps.setDouble(2, studentQualification.getPercentage());
					ps.setLong(3, studentQualification.getStID());
					ps.setObject(4, studentQualification.getQualificationType());
				});

	}

	@Override
	public List<GetStudentQualDetails> getStudentQualifications(Long stID) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select *,sq.id as sq_id from student_qualification sq" + " join qualification q"
				+ " on  sq.qualification_type=q.id" + " where st_id=?", ps -> {
					ps.setLong(1, stID);
				}, new GetStudentQualMapper());
	}

	@Override
	public void deleteStudentQualificationsByID(List<UUID> ID) {
		String deleteQuery = "delete from student_qualification where id=?";
		// TODO Auto-generated method stub
		jdbcTemplate.batchUpdate(deleteQuery, ID, ID.size(), (PreparedStatement ps, UUID id) -> {
			ps.setObject(1, id);
		});

	}

	@Override
	public void deleteStudentQualificationsByStID(Long id) {
		String deleteQuery = "delete from student_qualification where st_id=?";
		jdbcTemplate.update(deleteQuery, ps -> {
			ps.setLong(1, id);
		});

		// TODO Auto-generated method stub

	}

	private static final class StudentQualMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new StudentQualification((UUID) rs.getObject("id"), (UUID) rs.getObject("qualification_type"),
					rs.getString("qualification_stream"), rs.getDouble("percentage"), rs.getLong("st_id"));
		}

	}

	private static final class GetStudentQualMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new GetStudentQualDetails(rs.getString("name"), rs.getString("qualification_stream"),
					rs.getDouble("percentage"), (UUID) rs.getObject("qualification_type"),
					(UUID) rs.getObject("sq_id"));
		}
	}

}
