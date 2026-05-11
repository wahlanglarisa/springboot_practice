package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.ClassDayDao;
import com.larisa.dto.ClassDay;
@Service
public class ClassDayServiceImpl implements ClassDayService{
@Autowired
private ClassDayDao classDayDao;
	@Override
	public List<ClassDay> getClassDays() {
		// TODO Auto-generated method stub
		return classDayDao.getClassDays();
	}

}
