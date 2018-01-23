package com.ral.auth.service;


import com.ral.auth.model.entity.Module;

import java.util.List;

public interface IModuleService {

	List<Module> getModuleByProjectIdAndRoleId(String roleId, String projectId);
	
}
