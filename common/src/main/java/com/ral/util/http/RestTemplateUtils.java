package com.ral.util.http;

import java.util.Map;

import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author victor
 * @desc restful 模板 工具类
 */
public final class RestTemplateUtils {
	
	private static Logger logger = Logger.getLogger(RestTemplateUtils.class);

	private RestTemplateUtils(){
	}
	
	/**
	 * 请求JSON
	 * @param url 地址
	 * @param method 请求方式
	 * @param headers 头
	 * @param pathParams url参数
	 * @param body 请求体
	 * @return
	 */
	public static ResponseEntity<Object> requestJson(String url,HttpMethod method,Map<String, String> headers,Map<String, Object> pathParams,String body){
		ResponseEntity<Object> response = null;
		try {
			HttpHeaders header = new HttpHeaders();
			insertToHeader(header, headers);
			response = request(url, method, header, MediaType.APPLICATION_JSON_UTF8, pathParams, body);
		} catch (HttpClientErrorException | HttpServerErrorException e) {//表示服务方异常，仍可能存在response
			String res = e.getResponseBodyAsString();
			if(!StringUtils.isNullOrEmpty(res)){
				response = new ResponseEntity<Object>(JSONUtils.json2obj(res),e.getResponseHeaders(),e.getStatusCode());
			}else{
				response = new ResponseEntity<Object>(res,e.getResponseHeaders(),e.getStatusCode());
			}
			logger.error("Rest api ["+ url+"],server exception : " ,e);
		}catch (RestClientException e) {
			logger.error("Rest api ["+ url+"],client exception : " ,e);
		} catch (Exception e) {
			logger.error("Rest api ["+ url+"],exception : " ,e);
		}
		return response;
	}
	
	/**
	 * 请求JSON API
	 * @param url 请求地址
	 * @param method 方法
	 * @param pathParams url参数
	 * @param body 请求体
	 * @return
	 */
	public static ResponseEntity<Object> requestJson(String url,HttpMethod method,Map<String, Object> pathParams,String body){
		return requestJson(url,method,null,pathParams,body);
	}
	
	/**
	 * 请求json
	 * @param url url
	 * @param method 请求方式
	 * @param body 请求体
	 * @return
	 */
	public static ResponseEntity<Object> requestJson(String url,HttpMethod method,String body){
		return requestJson(url,method,null,null,body);
	}
	
	/**
	 * 发送HTTP请求
	 * @param url 请求地址
	 * @param method 方法
	 * @param headers 头
	 * @param contentType 请求类型
	 * @param pathParams url参数
	 * @param body 请求体
	 * @return 
	 */
	public static  ResponseEntity<Object> request(String url,HttpMethod method,HttpHeaders headers,MediaType contentType,Map<String, Object> pathParams, String body){
		RestTemplate rest = new RestTemplate();
		if(headers == null){
			headers = new HttpHeaders();
		}
		if(contentType != null){
			headers.setContentType(contentType);
		}
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		ResponseEntity<Object> response = null;
		if(pathParams == null || pathParams.isEmpty()){
			response = rest.exchange(url, method, entity, Object.class);
		}else{
			response = rest.exchange(url,method,entity,Object.class,pathParams);
		}
		return response;
	}
	
	private static void insertToHeader(HttpHeaders headers,Map<String, String> params){
		if(params!=null && !params.isEmpty()){
			params.keySet().forEach((key) -> {
				if(!StringUtils.isNullOrEmpty(params.get(key))){
					headers.add(key, params.get(key));
				}
			});
		}
	}
}
