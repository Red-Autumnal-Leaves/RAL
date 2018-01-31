package com.ral.util.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;


public class ActionUtil {

	private static Logger logger  = Logger.getLogger(ActionUtil.class);
	
	/**
	 * 
	 * @param request http请求对象
	 * @return
	 * @desc 获取tokenId,优先回去header中token, header没有时获取cookie
	 */
	public static String getToken(HttpServletRequest request){
		String tokenId = request.getHeader("token");
		if(StringUtils.isNullOrEmpty(tokenId)){
			tokenId = CookieUtils.getCookieValue(request, "token");
		}
		return tokenId;
	}
	
	/**
	 * 
	 * @param response
	 * @param text 字符串
	 * @desc 向客户端回写 字符串
	 */
	public static void ResponseText(HttpServletResponse response,String text){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Reponse get writer error." + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param text  JSON格式
	 * @desc 向客户端回写 JSON
	 */
	public static void ResponseJson(HttpServletResponse response,String text){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Reponse get writer error." + e.getMessage());
		}
	}


}
