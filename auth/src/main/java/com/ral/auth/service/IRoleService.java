package com.ral.auth.service;

import com.ral.auth.model.entity.Role;

/**
 * 
 * @author victor
 * @desc 角色servie
 */
public interface IRoleService {

	Role getRoleByRoleId(String roleId);
	
}
