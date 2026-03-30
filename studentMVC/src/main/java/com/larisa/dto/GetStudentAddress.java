package com.larisa.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentAddress {
	private UUID iUuid;
	private String stID, addressPermLine1, addressPermLine2, addressPermLine3, addressPreLine1, addressPreLine2,
			addressPreLine3, permCountryName, permStateName, permDistrictName, preCountryName, preStateName,
			preDistrictName;
	private long addrPermPin,addrPrePin;

}
