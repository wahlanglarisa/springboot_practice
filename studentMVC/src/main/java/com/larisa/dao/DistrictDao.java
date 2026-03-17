package com.larisa.dao;

import java.util.List;

import com.larisa.dto.District;

public interface DistrictDao {
	public List<District> getDistrictsByState(String stateCode);
}
