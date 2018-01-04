package com.ral.util.http;

import com.ral.util.codec.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author victor
 * http 工具类
 */
public class HttpUtils {

	/**
	 * 拼接URL
	 * @param url 拼接URL
	 * @param params url参数
	 * @return 
	 */
	public static String buildUrl(String url,Map<String, Object> params){
		String prefix = getUrlParamsByMap(params);
		if(StringUtils.isNullOrEmpty(prefix)){
			return url;
		}
		if(url.indexOf("?") > -1){
			if(url.lastIndexOf("?") == url.length() -1){
				return url + prefix;
			}else if(url.lastIndexOf("&") == url.length() -1){
				return url + prefix;
			}else{
				return url + "&" +  prefix;
			}
		}
		return url + "?" + prefix;
	}
	
	 /** 
	 * 将url参数转换成map 
	 * @param param aa=11&bb=22&cc=33 
	 * @return 
	 */  
	public static Map<String, Object> getUrlParams(String param) {  
	    Map<String, Object> map = new HashMap<String, Object>(0);  
	    if (StringUtils.isNullOrEmpty(param)) {  
	        return map;  
	    }  
	    String[] params = param.split("&");  
	    for (int i = 0; i < params.length; i++) {  
	        String[] p = params[i].split("=");  
	        if (p.length == 2) {  
	            map.put(p[0], p[1]);  
	        }  
	    }  
	    return map;  
	}  
	  
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, Object> map) {  
	    if (map == null || map.isEmpty()) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, Object> entry : map.entrySet()) {  
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");  
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
	    }  
	    return s;  
	} 
}
