package com.ral.auth.service;

import com.ral.auth.model.entity.Project;

import java.util.List;

public interface IProjectService {

	List<Project> getProjectsByRoleId(String roleId);
	
}
