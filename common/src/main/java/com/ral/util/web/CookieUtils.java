package com.ral.util.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CookieUtils {

	private static Logger logger = Logger.getLogger(CookieUtils.class);

	/**
	 * @param response 响应对象
	 * @param key cookie key
	 * @param value 内容
	 * @desc 往客户端浏览器写入cookie
	 */
	public static void writeCookie(HttpServletResponse response, String key, String value) {
		writeCookie(response, key, value, 1209600, "/");
	}
	
	/**
	 * @param response 响应对象
	 * @param key cookie key
	 * @param value 内容
	 * @param maxAge 保存时间，单位秒
	 * @desc 往客户端浏览器写入cookie
	 */
	public static void writeCookie(HttpServletResponse response, String key, String value,int maxAge) {
		writeCookie(response, key, value, maxAge, "/");
	}

	/**
	 * 
	 * @param response 响应对象
	 * @param key cookie key
	 * @param value 内容
	 * @param maxAge 保存时间，单位秒
	 * @param path 保存路径
	 * @desc 往客户端浏览器写入cookie
	 */
	public static void writeCookie(HttpServletResponse response, String key, String value, int maxAge, String path) {
		try {
			Cookie cookie = new Cookie(key, value);
			cookie.setMaxAge(maxAge);
			cookie.setPath(path);
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request 请求对象
	 * @param key cookie's key
	 * @return 返回内容,没有返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cc = request.getCookies();
		if (cc == null)
			return null;
		for (Cookie cookie : cc) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}


	/**
	 * 
	 * @param key 需要移除的key
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	public static void removeCookie(String key, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cc = request.getCookies();
		if ((cc == null) || (cc.length < 1)) {
			logger.error("Get cookie form request is null");
			return;
		}
		for (Cookie cookie : cc){
			if (cookie.getName().equals(key)) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
}
