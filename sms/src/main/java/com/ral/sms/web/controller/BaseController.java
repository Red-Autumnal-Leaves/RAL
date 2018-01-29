package com.ral.sms.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ral.model.auth.res.Manager;
import com.ral.model.domain.AccessToken;
import com.ral.service.session.ISessionService;
import com.ral.service.token.ITokenService;
import com.ral.sms.constants.SmsSystemConstants;
import com.ral.util.codec.StringUtils;
import com.ral.util.web.ActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author victor
 * @desc controller基类，所有的子包Controller应继承此类
 */
public abstract class BaseController {
	
	@Autowired
	protected ITokenService tokenService;
	
	@Autowired
	protected ISessionService sessionService;
	
	/**
	 * 
	 * @return
	 * @desc 从session中获取当前用户JSON信息，如没有获取到返回Null
	 */
	protected Manager getUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			return sessionService.getAttribute(tokenId, SmsSystemConstants.SESSION_USER_KEY, Manager.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @desc 获取当前用户Token信息，没有获取到返回Null
	 */
	protected AccessToken getToken(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			return tokenService.checkToken(tokenId);
		}
		return null;
	}
	
	protected void setSessionAttrbuite(String name,Object value){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			sessionService.setAttribute(tokenId, name, value);
		}
	}
	
	protected <T> T getSessionAttrbuite(String name,Class<T> clz){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			return sessionService.getAttribute(tokenId, name, clz);
		}
		return null;
	}
	
	protected <T> List<T> getSessionAttrbuiteAsList(String name,Class<T> clz){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			return sessionService.getAttributeAsList(tokenId, name, clz);
		}
		return null;
	}
	
	protected Map<String,Object> getSessionAttrbuiteAsList(String name){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenId = ActionUtil.getToken(request);
		if(!StringUtils.isNullOrEmpty(tokenId)){
			return sessionService.getAttributeAsMap(tokenId, name);
		}
		return null;
	}

}
