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
public class StudentAddress {
	private UUID iUuid;
	private String stID, addressPermLine1, addressPermLine2, addressPermLine3, addressPreLine1, addressPreLine2,
			addressPreLine3, permCountryCode, permStateCode, permDistrictCode, preCountryCode, preStateCode,
			preDistrictCode;
	private long addrPermPin,addrPrePin;
}
