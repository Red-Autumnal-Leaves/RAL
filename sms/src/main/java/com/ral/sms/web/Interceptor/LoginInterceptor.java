package com.ral.sms.web.Interceptor;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ral.model.auth.res.Manager;
import com.ral.model.domain.AccessToken;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.res.Result;
import com.ral.service.session.ISessionService;
import com.ral.service.token.ITokenService;
import com.ral.sms.business.user.impl.UserBusinessImpl;
import com.ral.sms.constants.SmsSystemConstants;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.web.ActionUtil;
import com.ral.util.web.CookieUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author victor
 * @desc 登录拦截器
 * <ul>
 * <li>校验Cookie中的token信息，正常情况下次校验已经足够</li>
 * <li>校验Reuqest Header中的token,调试使用,方便工具进行请求</li>
 * <li>请求完成对用户token和session进行时间刷新</li>
 * </ul>
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger  = Logger.getLogger(LoginInterceptor.class);

	@Autowired
	private ITokenService tokenService;
	
	@Autowired
	private ISessionService sessionService;
	
	private final static List<String> ignores = new ArrayList<>();
	
	//无需校验的请求
	static{
		ignores.add("login");//登录
		ignores.add("loginout");//注销
		ignores.add("error");//错误
		ignores.add("example");//示例
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		String path = request.getServletPath();
		for(String ignore : ignores){
			if(path.contains(ignore)){
				return;
			}
		}
		//刷新token和session过期时间
		String tokenId = ActionUtil.getToken(request);
		//cookie重写token 有效时间30分钟
		CookieUtils.writeCookie(response, "token", tokenId, SmsSystemConstants.SESSION_TIME_OUT);
		//刷新redis中的token有效时间
		tokenService.refresh(tokenId, SmsSystemConstants.SESSION_TIME_OUT);
		//刷新session过期时间
		sessionService.createSession(tokenId,SmsSystemConstants.SESSION_TIME_OUT);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		for(String ignore : ignores){
			if(path.contains(ignore)){
				return true;
			}
		}
		
		String tokenId = ActionUtil.getToken(request);
		//token为空
		if(StringUtils.isNullOrEmpty(tokenId)){
			Result modal = new Result(HttpStatusEnum.NOT_AUTHORIZED,"请求未授权,未获取到登录信息！",false);
			ActionUtil.ResponseJson(response, JSONUtils.toJson(modal));
			return false;
		}
		//token非法,或者已经过期
		AccessToken token = tokenService.checkToken(tokenId);
		if(token == null) {
			Result modal = new Result(HttpStatusEnum.NOT_AUTHORIZED,"授权信息无效或登录超时,请重新登录！",false);
			ActionUtil.ResponseJson(response, JSONUtils.toJson(modal));
			return false;
		}

		Manager manager = sessionService.getAttribute(tokenId, SmsSystemConstants.SESSION_USER_KEY, Manager.class);
		if(manager != null){
			logger.info("[" + request.getRemoteAddr() + "][" + manager.getUserId() + "]["+ request.getMethod() + "]" + request.getContextPath() + request.getServletPath() );
		}
		return true;
	}
	

}
