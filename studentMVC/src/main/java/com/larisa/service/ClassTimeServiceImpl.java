package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.ClassTimeDao;
import com.larisa.dto.ClassTime;
@Service
public class ClassTimeServiceImpl implements ClassTimeService {
@Autowired
private ClassTimeDao classTimeDao;
	@Override
	public List<ClassTime> getAvailableClassTimes(String day) {
		// TODO Auto-generated method stub
		return classTimeDao.getAvailableClassTimes(day);
	}

}
