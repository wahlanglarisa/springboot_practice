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

import com.larisa.dto.StudentAddress;
import com.larisa.dto.UserStudent;
@Repository 
public class StudentAddressDaoImpl implements StudentAddressDao {
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
			ps.setString(1, (userStudent.getPermaddressline1()==null ? studentAddresses.getAddressPermLine1()
					: userStudent.getPermaddressline1()));
			ps.setString(2, (userStudent.getPermaddressline2()==null ? studentAddresses.getAddressPermLine2()
					: userStudent.getPermaddressline2()));
			ps.setString(3, (userStudent.getPermaddressline3()==null ? studentAddresses.getAddressPermLine3()
					: userStudent.getPermaddressline3()));
			ps.setString(4, (userStudent.getPermCountryCode()==null ? studentAddresses.getPermCountryCode()
					: userStudent.getPermCountryCode()));
			ps.setString(5, (userStudent.getPermStateCode()==null ? studentAddresses.getPermStateCode()
					: userStudent.getPermStateCode()));
			ps.setString(6, (userStudent.getPermDistrictCode()==null ? studentAddresses.getPermDistrictCode()
					: userStudent.getPermDistrictCode()));
			ps.setString(7, (userStudent.getPreaddressline1()==null ? studentAddresses.getAddressPreLine1()
					: userStudent.getPreaddressline1()));
			ps.setString(8, (userStudent.getPreaddressline2()==null ? studentAddresses.getAddressPreLine2()
					: userStudent.getPreaddressline2()));
			ps.setString(9, (userStudent.getPreaddressline3()==null ? studentAddresses.getAddressPreLine3()
					: userStudent.getPreaddressline3()));
			ps.setString(10, (userStudent.getPreCountryCode()==null ? studentAddresses.getPreCountryCode()
					: userStudent.getPreCountryCode()));
			ps.setString(11, (userStudent.getPreStateCode()==null ? studentAddresses.getPreStateCode()
					: userStudent.getPreStateCode()));
			ps.setString(12, (userStudent.getPreDistrictCode()==null ? studentAddresses.getPreDistrictCode()
					: userStudent.getPreDistrictCode()));
			ps.setLong(13, (userStudent.getPermaddrpin() == 0 ? studentAddresses.getAddrPermPin()
					: userStudent.getPermaddrpin()));
			ps.setLong(14, (userStudent.getPreaddrpin() == 0 ? studentAddresses.getAddrPrePin()
					: userStudent.getPreaddrpin()));
			ps.setLong(15, userStudent.getSt_id());
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
}
