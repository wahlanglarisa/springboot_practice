package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.DistrictDao;
import com.larisa.dto.District;
@Service
public class DistrictServiceImpl implements DistrictService{
	@Autowired
	private DistrictDao districtDao;
	@Override
	public List<District> getDistrictsByState(String stateCode) {
		// TODO Auto-generated method stub
		return districtDao.getDistrictsByState(stateCode);
	}

}
 