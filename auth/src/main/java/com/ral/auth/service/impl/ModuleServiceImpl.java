package com.ral.auth.service.impl;

import com.ral.auth.dao.ModuleMapper;
import com.ral.auth.model.entity.Module;
import com.ral.auth.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author victor
 * @desc 
 */
@Service
public class ModuleServiceImpl implements IModuleService {
	
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> getModuleByProjectIdAndRoleId(String roleId, String projectId) {
		List<Module> modules = moduleMapper.selectByByProjectIdAndRoleId(roleId,projectId);
		return modules == null ? new ArrayList<>() : modules;
	}

}
