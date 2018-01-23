package com.ral.auth.service.impl;

import com.ral.auth.dao.RoleMapper;
import com.ral.auth.model.entity.Role;
import com.ral.auth.model.entity.RoleExample;
import com.ral.auth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Role getRoleByRoleId(String roleId) {
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<Role> roles = roleMapper.selectByExample(example);
		if(roles == null || roles.isEmpty()){
			return null;
		}
		return roles.get(0);
	}

}
