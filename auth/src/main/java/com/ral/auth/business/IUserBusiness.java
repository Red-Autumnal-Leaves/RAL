package com.ral.auth.business;

import com.ral.model.res.Result;

public interface IUserBusiness {

	Result login(String tenantId, String body);
	
}
