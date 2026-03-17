package com.larisa.service;

import java.util.List;

import com.larisa.dto.District;

public interface DistrictService {
	public List<District> getDistrictsByState(String stateCode);

}
