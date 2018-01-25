package com.ral.sms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ral.model.res.Result;
import com.ral.sms.business.user.IUserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author vic
 * @desc 登录控制器
 */
@RestController
public class LoginController {
	
	@Autowired
	private IUserBusiness userBusiness;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param body 请求参数体 JSON格式
	 * @return
	 * @desc 管理员登录入口，登录成功创建token 和 session
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json")
	public Result login(HttpServletRequest request, HttpServletResponse response, @RequestBody String body){
		return userBusiness.login(request,response,body);
	}
	
	/**
	 * 
	 * @return
	 * @desc 用于用户时时查询个人信息,方便页面刷新
	 */
	@RequestMapping(value = "/token",method = RequestMethod.GET)
	public Result getTokens(HttpServletRequest request,HttpServletResponse response){
		return userBusiness.token(request, response);
	}
	
	/**
	 * 
	 * @return
	 * @desc 注销，销毁token,session,cookie
	 */
	@RequestMapping(value = "/loginout",method = RequestMethod.DELETE)
	public Result loginout(HttpServletRequest request,HttpServletResponse response){
		return userBusiness.loginout(request, response);
	}
	
		
}
