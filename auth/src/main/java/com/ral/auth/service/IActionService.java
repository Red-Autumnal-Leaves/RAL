package com.ral.auth.service;


import com.ral.auth.model.entity.Action;

import java.util.List;

public interface IActionService {

	List<Action> getByRoleIdAndModuleId(String roleId, String moduleId);
}
