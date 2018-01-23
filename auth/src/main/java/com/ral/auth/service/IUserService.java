package com.ral.auth.service;

import com.ral.auth.model.entity.User;

public interface IUserService {

	User getUserByUserId(String userId);
	
	User getUserByUserName(String tenantId, String userName);
	
}
