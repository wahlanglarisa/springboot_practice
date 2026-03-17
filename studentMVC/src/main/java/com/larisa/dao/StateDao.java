package com.larisa.dao;

import java.util.List;

import com.larisa.dto.State;

public interface StateDao {
	public List<State> getStatesByCountry_code(String countryCode);
}
