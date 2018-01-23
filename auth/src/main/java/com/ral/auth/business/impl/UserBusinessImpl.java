package com.ral.auth.business.impl;

import java.util.Map;

import com.ral.auth.business.IUserBusiness;
import com.ral.auth.model.entity.*;
import com.ral.auth.model.vo.*;
import com.ral.auth.service.*;
import com.ral.exception.ParamsException;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.res.Result;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.MD5Utils;
import com.ral.util.codec.StringUtils;
import com.ral.util.date.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserBusinessImpl implements IUserBusiness {
	
	@Autowired
	private ITenantService tenantService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IModuleService moduleService;
	
	@Autowired
	private IActionService actionService;

	@Override
	public Result login(String tenantId, String body) {
		if(StringUtils.isNullOrEmpty(tenantId)){
			throw new ParamsException("租户ID不能为空！");
		}
		if(!StringUtils.isJson(body)){
			throw new ParamsException("请求体格式不正确！");
		}
		Map<String, Object> params = JSONUtils.toMap(body);
		if(StringUtils.isNullOrEmpty(params.get("username")) || StringUtils.isNullOrEmpty(params.get("password"))){
			throw new ParamsException("用户名或者密码不能为空！");
		}
		Tenant tenant = tenantService.getByTenantId(tenantId);
		if(tenant == null){
			throw new ParamsException("租户ID不存在！");
		}
		if(!tenant.getIsEnable()){
			return new Result(HttpStatusEnum.NOT_AUTHORIZED, "租户被禁用，请联系管理员！", false);
		}
		if(DateUtils.isOld(tenant.getAuthorizeTime())){
			return new Result(HttpStatusEnum.NOT_AUTHORIZED, "租户授权过期，请重新授权！", false);
		}
		String userName = params.get("username").toString();
		String password = params.get("password").toString();
		User user = userService.getUserByUserName(tenantId, userName);
		if(user == null || !MD5Utils.check(password, user.getPassword())){
			return new Result(HttpStatusEnum.OK, "登录失败,用户名或者密码错误！", false);
		}
		if(StringUtils.isNullOrEmpty(user.getRoleId())){
			return new Result(HttpStatusEnum.OK, "登录失败,用户身份异常！", false);
		}
		Role role = roleService.getRoleByRoleId(user.getRoleId());
		if(role == null || role.getIsDelete()){
			return new Result(HttpStatusEnum.OK, "登录失败,用户身份异常！", false);
		}
		UserVo data  = intLoginUser(tenant,user, role);
		return Result.initSuccessResult(data, null);
	}
	
	/**
	 * 获取用户信息载体，考虑是否存储缓存
	 */
	private UserVo intLoginUser(Tenant tenant, User user, Role role){
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user,userVo);
		RoleVo roleVo = new RoleVo();
		BeanUtils.copyProperties(role,roleVo);
		userVo.setRole(roleVo);
		List<Project> projects = projectService.getProjectsByRoleId(role.getRoleId());
		if(projects != null){
			projects.forEach((project) -> {
				ProjectVo projectVo = new ProjectVo();
				BeanUtils.copyProperties(project,projectVo);
				List<Module> modules = moduleService.getModuleByProjectIdAndRoleId(role.getRoleId(), project.getProjectId());
				if(modules != null){
					modules.forEach((module)->{
						ModuleVo moduleVo = new ModuleVo();
						BeanUtils.copyProperties(module,moduleVo);
						List<Action> actions = actionService.getByRoleIdAndModuleId(role.getRoleId(), module.getModuleId());
						if(actions != null){
							actions.forEach((action) -> {
								ActionVo actionVo = new ActionVo();
								BeanUtils.copyProperties(action,actionVo);
								moduleVo.getActions().add(actionVo);
							});
						}
						projectVo.getServices().add(moduleVo);
					});
				}
				userVo.getProjects().add(projectVo);
			});
		}
		return userVo;
	}

}
