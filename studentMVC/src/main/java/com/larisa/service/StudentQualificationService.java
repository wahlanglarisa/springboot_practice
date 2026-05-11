package com.larisa.service;

import java.util.List;
import java.util.UUID;

import com.larisa.dto.GetStudentQualDetails;
import com.larisa.dto.StudentQualification;

public interface StudentQualificationService {
	public List<GetStudentQualDetails> getStudentQualifications(Long stID);
	public void deleteStudentQualificationsByID(List<UUID> ID);

}
