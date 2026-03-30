package com.larisa.dao;

import com.larisa.dto.GetStudentAddress;
import com.larisa.dto.StudentAddress;
import com.larisa.dto.UserStudent;

public interface StudentAddressDao {
 public void updateAddress(UserStudent userStudent);
 public GetStudentAddress getAddressByStID(long stID);
 public void deleteByStID(long ID);
}
