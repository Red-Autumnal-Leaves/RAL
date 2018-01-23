package com.ral.auth.service.impl;

import com.ral.auth.dao.UserMapper;
import com.ral.auth.model.entity.User;
import com.ral.auth.model.entity.UserExample;
import com.ral.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByUserId(String userId) {
		UserExample example = new UserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<User> users  = userMapper.selectByExample(example);
		if(users == null || users.isEmpty()){
			return null;
		}
		return users.get(0);
	}

	@Override
	public User getUserByUserName(String tenantId, String userName) {
		UserExample example = new UserExample();
		example.createCriteria().andTenantIdEqualTo(tenantId).andUserNameEqualTo(userName);
		List<User> users  = userMapper.selectByExample(example);
		if(users == null || users.isEmpty()){
			return null;
		}
		return users.get(0);
	}


}
