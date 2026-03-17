package com.larisa.service;

import java.util.List;

import com.larisa.dto.State;

public interface StateService {
	public List<State> getStatesByCountry_code(String countryCode);

}
