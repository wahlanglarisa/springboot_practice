package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.StateDao;
import com.larisa.dto.State;
@Service
public class StateServiceImpl implements StateService {
	@Autowired
	private StateDao stateDao;
	@Override
	public List<State> getStatesByCountry_code(String countryCode) {
		// TODO Auto-generated method stub
		return stateDao.getStatesByCountry_code(countryCode);
	}

}
