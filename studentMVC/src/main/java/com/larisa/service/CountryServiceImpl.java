package com.larisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larisa.dao.CountryDao;
import com.larisa.dto.Country;
@Service
public class CountryServiceImpl implements CountryService{
	@Autowired
	private CountryDao countryDao;
	
	@Override
	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return countryDao.getCountries();
	}

}
