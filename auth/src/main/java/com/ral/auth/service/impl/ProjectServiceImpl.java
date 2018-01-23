package com.ral.auth.service.impl;

import com.ral.auth.dao.ProjectMapper;
import com.ral.auth.model.entity.Project;
import com.ral.auth.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<Project> getProjectsByRoleId(String roleId) {
		List<Project> list = projectMapper.selectByRoleId(roleId);
		return list == null ? new ArrayList<>() : list;
	}

}
