package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larisa.dto.Course;
import com.larisa.dto.GetAllStudentData;
import com.larisa.dto.Student;
import com.larisa.dto.UserStudent;

import lombok.Setter;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Student findStudentByEmail(String email) {
		// TODO Auto-generated method stub
		String getQueryString = "select * from student where student.email=?";

		List<Student> student = (List<Student>) (jdbcTemplate.query(getQueryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
				;
			};
		}, new StudentMapper()));
		return (student.size() == 0 ? null : student.getFirst());
	}

	@Override
	public Student findStudentByPhoneNo(Long phone_no) {
		// TODO Auto-generated method stub
		String getQueryString = "select * from student where student.phone_no=?";

		List<Student> student = (List<Student>) (jdbcTemplate.query(getQueryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, phone_no);
			};
		}, new StudentMapper()));
		return (student.size() == 0 ? null : student.getFirst());
	}

	@Override
	public Page<Student> getListOfStudents(Pageable pageable) {
		String queryString = "select * from student limit ? offset ?";
		List<Student> students = (List<Student>) jdbcTemplate.query(queryString, ps -> {
			ps.setInt(1, pageable.getPageSize());
			ps.setLong(2, pageable.getOffset());
		}, new StudentMapper());
		String rowStringQuery = "select count(*) from student";
		int totalRows = jdbcTemplate.queryForObject(rowStringQuery, Integer.class);
		// TODO Auto-generated method stub
		return new PageImpl<Student>(students, pageable, totalRows);

	}

	@Override
	public GetAllStudentData getAllStudentData(long id) {
		String queryString = "SELECT\r\n" + "	st.* FROM\r\n" + "	STUDENT ST\r\n"

				+ " where st.id=?";
		// TODO Auto-generated method stub
		return (GetAllStudentData) jdbcTemplate.query(queryString, ps -> {
			ps.setLong(1, id);
		}, new GetAllStudentDataMapper()).getFirst();
	}

	@Override
	public void addStudent(Student st) throws DuplicateKeyException {
		String queryString = "insert into student(phone_no," + "email"
				+ ",last_name,first_name,user_id,profile_picture)" + " values(?,?,?,?,?,?)";
		jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, st.getPhone_no());
				ps.setString(2, st.getEmail());
				ps.setString(3, st.getLast_name());
				ps.setString(4, st.getFirst_name());
				ps.setString(5, st.getUser_id());
				ps.setBytes(6, st.getProfile_picture());

			}
		});
		Student student = findStudentByEmail(st.getEmail());
		String addStaddressString = "insert into student_address(st_id) values(?)";
		jdbcTemplate.update(addStaddressString, (ps) -> {
			ps.setLong(1, student.getId());
		});
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(Student st) {
		String getStudentQuery = "select * from student where id=?";
		List<Student> students = (List<Student>) jdbcTemplate.query(getStudentQuery, ps -> {
			ps.setLong(1, st.getId());
		}, new StudentMapper());
		Student student = students.getFirst();
		String queryString = "update student set phone_no=?,email=?"
				+ ",last_name=?,first_name=?,profile_picture=? where id=?";
		System.out.println("Profile Picture" + st.getProfile_picture());
		System.out.println("Profile Picture existing student " + student.getProfile_picture());
		jdbcTemplate.update(queryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, st.getPhone_no() == null ? student.getPhone_no() : st.getPhone_no());
				ps.setString(2, (st.getEmail() == "" || st.getEmail() == null) ? student.getEmail() : st.getEmail());

				ps.setString(3, (st.getLast_name() == "" || st.getLast_name() == null) ? student.getLast_name()
						: st.getLast_name());
				ps.setString(4, (st.getFirst_name() == null || st.getFirst_name() == null) ? student.getFirst_name()
						: st.getFirst_name());

				ps.setBytes(5,
						(st.getProfile_picture() == null) ? student.getProfile_picture() : st.getProfile_picture());

				ps.setLong(6, st.getId());
			}
		});
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteStudent(Student st) {
		// TODO Auto-generated method stub
		String deleteQuery = "delete from student where id=?";
		jdbcTemplate.update(deleteQuery, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, st.getId());
			};
		});
	}

	private static final class StudentMapper implements RowMapper {

		@Override
		public @Nullable Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			// TODO Auto-generated method stub
			student.setId(rs.getLong("id"));

			student.setEmail(rs.getString("email"));
			student.setPhone_no(rs.getLong("phone_no"));
			student.setLast_name(rs.getString("last_name"));
			student.setFirst_name(rs.getString("first_name"));
			student.setUser_id(rs.getString("user_id"));
			student.setProfile_picture(rs.getBytes("profile_picture"));
			return student;
		}

	}

	@Override
	public Student getStudent(long id) {
		// TODO Auto-generated method stub
		String getQueryString = "select * from student where student.id=?";

		Student student = (Student) (jdbcTemplate.query(getQueryString, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setLong(1, id);
			};
		}, new StudentMapper())).get(0);
		return student;
	}

	@Override
	public long getCountCourses(long stID) {
		// TODO Auto-generated method stub
		try {
			String getCountCoursesQuery = "select count(*)  from course_student where st_id=? group by st_id";
			Long noOfCourses = jdbcTemplate.queryForObject(getCountCoursesQuery, new Object[] { stID }, Long.class);
			return noOfCourses;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public long getCountofStudents() {
		String rowStringQuery = "select count(*) from student";
		int totalRows = jdbcTemplate.queryForObject(rowStringQuery, Integer.class);
		return totalRows;
	}

	private static final class GetAllStudentDataMapper implements RowMapper<GetAllStudentData> {

		private ObjectMapper mapper = new ObjectMapper();

		@Override
		public GetAllStudentData mapRow(ResultSet rs, int rowNum) throws SQLException {

			return new GetAllStudentData(rs.getLong("phone_no"), rs.getLong("id"), rs.getString("email"),
					rs.getString("last_name"), rs.getString("first_name"), rs.getString("user_id"),
					rs.getBytes("profile_picture"));
		}
	}
}
