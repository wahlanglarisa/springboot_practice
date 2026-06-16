package com.larisa.dao;

import java.util.List;

import com.larisa.dto.ClassTime;

public interface ClassTimeDao {
	public List<ClassTime> getAvailableClassTimes(String day);
}
