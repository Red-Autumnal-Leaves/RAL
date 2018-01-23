package com.ral.auth.service.impl;


import com.ral.auth.dao.ActionMapper;
import com.ral.auth.model.entity.Action;
import com.ral.auth.service.IActionService;
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
public class ActionServiceImpl implements IActionService {

	@Autowired
	private ActionMapper actionMapper;
	
	@Override
	public List<Action> getByRoleIdAndModuleId(String roleId, String moduleId) {
		List<Action> actions = actionMapper.selectByRoleIdAndMoudleId(roleId,moduleId);
		return actions == null ? new ArrayList<>() : actions;
	}

}
