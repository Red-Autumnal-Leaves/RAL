package com.ral.auth.service;

import com.ral.auth.model.entity.Tenant;

public interface ITenantService {

	Tenant getByTenantId(String tenantId);
	
	int update(Tenant tenant);
	
	int insert(Tenant tenant);
	
}
