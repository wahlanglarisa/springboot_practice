package com.larisa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.StudentQualificationDao;
import com.larisa.dto.GetStudentQualDetails;
import com.larisa.dto.StudentQualification;
@Service
public class StudentQualificationServiceImpl implements StudentQualificationService {
@Override
	public void deleteStudentQualificationsByID(List<UUID> ID) {
		// TODO Auto-generated method stub
		qualificationDao.deleteStudentQualificationsByID(ID);
		
	}
@Autowired
private StudentQualificationDao qualificationDao;
	@Override
	public List<GetStudentQualDetails> getStudentQualifications(Long stID) {
		// TODO Auto-generated method stub
		return qualificationDao.getStudentQualifications(stID);
	}

}
