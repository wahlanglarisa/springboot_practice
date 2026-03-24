package com.larisa.dao;

import java.util.UUID;

import com.larisa.dto.UserCreationStatus;

public interface UserCreationStatusDao {
	public UserCreationStatus getByStatusName(String statusName);
	public UserCreationStatus getByStatusByID(UUID id);

}
