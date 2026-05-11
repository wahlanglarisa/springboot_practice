package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.Professor;

@Repository
public class ProfessorDaoImpl implements ProfessorDao {
	@Override
	public List<Professor> getProfessors() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from professor", new ProfessorMapper());
	}
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveProfessor(Professor professor) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
				"insert into professor(first_name,middle_name,last_name,user_id,phone_no) values(?,?,?,?,?)",
				ps -> {
					ps.setString(1, professor.getFirstName());
					ps.setString(2, professor.getMiddleName());
					ps.setString(3,professor.getLastName());
					ps.setString(4, professor.getUserID());
					ps.setLong(5, professor.getPhoneNo());
				});

	}

	@Override
	public Professor getByPhoneNumber(Long phoneNo) {
		String queryString="select * from professor where phone_no=?";
		// TODO Auto-generated method stub
		List<Professor> professor=jdbcTemplate.query(queryString, ps->{ps.setLong(1, phoneNo);}, new ProfessorMapper());
		return (professor.size()!=0?professor.getFirst():null);
	}
	private static final class ProfessorMapper implements RowMapper{

		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Professor(rs.getString("first_name"), rs.getString("last_name"), rs.getString("middle_name"),(rs.getString("first_name")+" "+ rs.getString("last_name")),rs.getString("user_id"),(UUID)rs.getObject("id"), rs.getLong("phone_no")) ;
		}
		
	}

}
