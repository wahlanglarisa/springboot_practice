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

import com.larisa.dto.GetStudentAddress;
import com.larisa.dto.StudentAddress;
import com.larisa.dto.UserStudent;

@Repository
public class StudentAddressDaoImpl implements StudentAddressDao {
	@Override
	public GetStudentAddress getAddressByStID(long stID) {
		// TODO Auto-generated method stub
		String getAddressStudent = "SELECT\r\n" + "	STUDENT_ADDRESS.*,\r\n" + "	CO.COUNTRY_NAME PERM_COUNTRY_NAME,\r\n"
				+ "	CO2.COUNTRY_NAME PRE_COUNTRY_NAME,\r\n" + "	ST1.STATE_NAME PERM_STATE_NAME,\r\n"
				+ "	ST2.STATE_NAME PRE_STATE_NAME,\r\n" + "	D1.DISTRICT_NAME PERM_DISTRICT_NAME,\r\n"
				+ "	D2.DISTRICT_NAME PRE_DISTRICT_NAME\r\n" + "FROM\r\n" + "	STUDENT_ADDRESS\r\n"
				+ "	left JOIN COUNTRY CO ON PERM_COUNTRY_CODE = CO.COUNTRY_CODE\r\n"
				+ "	left JOIN COUNTRY CO2 ON PRESENT_COUNTRY_CODE = CO2.COUNTRY_CODE\r\n"
				+ "	left JOIN STATE ST1 ON PERM_STATE_CODE = ST1.STATE_CODE\r\n"
				+ "	left JOIN STATE ST2 ON PRE_STATE_CODE = ST2.STATE_CODE\r\n"
				+ "	left JOIN DISTRICT D1 ON D1.DISTRICT_CODE = PERM_DISTRICT_CODE\r\n"
				+ "	left JOIN DISTRICT D2 ON D2.DISTRICT_CODE = PRE_DISTRICT_CODE \r\n" + "	where st_id=?";
		GetStudentAddress studentAddresses = (GetStudentAddress) (jdbcTemplate.query(getAddressStudent, (ps) -> {
			ps.setLong(1, stID);
		}, new GetStudentAddressMapper())).getFirst();
		return studentAddresses;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void updateAddress(UserStudent userStudent) {
		// TODO Auto-generated method stub
		String getAddressStudent = "select * from student_address where st_id=?";
		StudentAddress studentAddresses = (StudentAddress) (jdbcTemplate.query(getAddressStudent, (ps) -> {
			ps.setLong(1, userStudent.getSt_id());
		}, new StudentAddressMapper())).getFirst();
		String updateAddressStudent = "update student_address set address_perm_1=?,address_perm_2=?,address_perm_3=?,"
				+ "perm_country_code=?,perm_state_code=?,perm_district_code=?,"
				+ "address_present_1=?,address_present_2=?,address_present_3=?,"
				+ "present_country_code=?,pre_state_code=?,pre_district_code=?,"
				+ "address_perm_pin=?,address_present_pin=? where st_id=?";
		jdbcTemplate.update(updateAddressStudent, ps -> {
			ps.setString(1, (userStudent.getPermaddressline1() == null ? studentAddresses.getAddressPermLine1()
					: userStudent.getPermaddressline1()));
			ps.setString(2, (userStudent.getPermaddressline2() == null ? studentAddresses.getAddressPermLine2()
					: userStudent.getPermaddressline2()));
			ps.setString(3, (userStudent.getPermaddressline3() == null ? studentAddresses.getAddressPermLine3()
					: userStudent.getPermaddressline3()));
			ps.setString(4, (userStudent.getPermCountryCode() == null ? studentAddresses.getPermCountryCode()
					: userStudent.getPermCountryCode()));
			ps.setString(5, (userStudent.getPermStateCode() == null ? studentAddresses.getPermStateCode()
					: userStudent.getPermStateCode()));
			ps.setString(6, (userStudent.getPermDistrictCode() == null ? studentAddresses.getPermDistrictCode()
					: userStudent.getPermDistrictCode()));
			ps.setString(7, (userStudent.getPreaddressline1() == null ? studentAddresses.getAddressPreLine1()
					: userStudent.getPreaddressline1()));
			ps.setString(8, (userStudent.getPreaddressline2() == null ? studentAddresses.getAddressPreLine2()
					: userStudent.getPreaddressline2()));
			ps.setString(9, (userStudent.getPreaddressline3() == null ? studentAddresses.getAddressPreLine3()
					: userStudent.getPreaddressline3()));
			ps.setString(10, (userStudent.getPreCountryCode() == null ? studentAddresses.getPreCountryCode()
					: userStudent.getPreCountryCode()));
			ps.setString(11, (userStudent.getPreStateCode() == null ? studentAddresses.getPreStateCode()
					: userStudent.getPreStateCode()));
			ps.setString(12, (userStudent.getPreDistrictCode() == null ? studentAddresses.getPreDistrictCode()
					: userStudent.getPreDistrictCode()));
			ps.setLong(13, (userStudent.getPermaddrpin() == 0 ? studentAddresses.getAddrPermPin()
					: userStudent.getPermaddrpin()));
			ps.setLong(14, (userStudent.getPreaddrpin() == 0 ? studentAddresses.getAddrPrePin()
					: userStudent.getPreaddrpin()));
			ps.setLong(15, userStudent.getSt_id());
		});

	}

	@Override
	public void deleteByStID(long ID) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from student_address where st_id=?", ps -> {
			ps.setLong(1, ID);
		});
	}

	private static final class StudentAddressMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new StudentAddress((UUID) rs.getObject("id"), rs.getString("st_id"), rs.getString("address_perm_1"),
					rs.getString("address_perm_2"), rs.getString("address_perm_3"), rs.getString("address_present_1"),
					rs.getString("address_present_2"), rs.getString("address_present_3"),
					rs.getString("perm_country_code"), rs.getString("perm_state_code"),
					rs.getString("perm_district_code"), rs.getString("present_country_code"),
					rs.getString("pre_state_code"), rs.getString("pre_district_code"), rs.getLong("address_perm_pin"),
					rs.getLong("address_present_pin"));
		}
	}

	private static final class GetStudentAddressMapper implements RowMapper {
		@Override
		public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new GetStudentAddress((UUID) rs.getObject("id"), rs.getString("st_id"),
					rs.getString("address_perm_1"), rs.getString("address_perm_2"), rs.getString("address_perm_3"),
					rs.getString("address_present_1"), rs.getString("address_present_2"),
					rs.getString("address_present_3"), rs.getString("perm_country_name"),
					rs.getString("perm_state_name"), rs.getString("perm_district_name"),
					rs.getString("pre_country_name"), rs.getString("pre_state_name"), rs.getString("pre_district_name"),
					rs.getLong("address_perm_pin"), rs.getLong("address_present_pin"));
		}
	}
}
