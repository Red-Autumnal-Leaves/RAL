package com.ral.auth.business;

import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

public interface IUserBusiness {

	Result login(HttpServletRequest request,String tenantId, String body);
	
}
