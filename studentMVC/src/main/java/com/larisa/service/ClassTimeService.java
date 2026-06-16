package com.larisa.service;

import java.util.List;

import com.larisa.dto.ClassTime;

public interface ClassTimeService {
	public List<ClassTime> getAvailableClassTimes(String day);

}
