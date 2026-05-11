package com.larisa.dao;

import java.util.List;
import java.util.UUID;

import com.larisa.dto.GetStudentQualDetails;
import com.larisa.dto.StudentQualification;

public interface StudentQualificationDao {
	public void addQualifications(List<StudentQualification> studentQualifications);
	public List<GetStudentQualDetails> getStudentQualifications(Long stID);
	public void deleteStudentQualificationsByID(List<UUID> ID);
	public void deleteStudentQualificationsByStID(Long id);


}
