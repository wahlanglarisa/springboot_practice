package com.larisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.StudentAddressDao;
import com.larisa.dto.GetStudentAddress;
import com.larisa.dto.StudentAddress;
@Service
public class StudentAddressServiceImpl implements StudentAddressService {
	@Autowired
	private StudentAddressDao studentAddressDao;
	@Override
	public GetStudentAddress getAddressByStID(long stID) {
		// TODO Auto-generated method stub
		return studentAddressDao.getAddressByStID(stID);
	}

}
