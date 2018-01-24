package com.ral.auth.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.ral.auth.business.IUserBusiness;
import com.ral.model.res.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Autowired
	private IUserBusiness userBusiness;
	
	/**
	 * 登录API
	 * @param request
	 * @param tenantId 租户id
	 * @param body 请求体
	 * @return
	 */
	@RequestMapping(value = "/{tenantId}/login",method = RequestMethod.POST,produces="application/json")
	public Result login(HttpServletRequest request, @PathVariable("tenantId")String tenantId, @RequestBody String body){
		return userBusiness.login(request,tenantId, body);
	}

}
