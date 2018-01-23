package com.ral.auth.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ral.exception.ParamsException;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.res.Result;
import com.ral.util.codec.BeanUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author victor
 * @desc 统一异常处理Controller
 */
@Controller
public class ExceptionController extends BasicErrorController {

	@Autowired
	public ExceptionController(ServerProperties serverProperties) {
		super(new DefaultErrorAttributes(), serverProperties.getError());
	}

	/**
	 * 此mapping会拦截所有非text/html的异常,一般mapping到非浏览器请求 特殊处理switch
	 */
	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> error = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
		
		//异常判断
		Object exception = error.get("exception");
		if(exception != null){
			Result result = exception(exception,error.get("message").toString());
			if(result != null){
				Map<String, Object> map = BeanUtils.beanToMap(result);
				return new ResponseEntity<Map<String, Object>>(map, getStatus(request));
			}
		}
		//其他错误
		HttpStatus status = getStatus(request);
		switch (status.value()) {
			case 400:
				return this.badRequest(request);
			case 403:
				return this.notAuthorized(request);
			case 404:
				return this.notfound(request);
			case 500:
				return this.exception(request);
		}
		// default
		Result resp = new Result(HttpStatusEnum.ERROR, false);
		resp.setStatus(status.value());
		resp.setMsg(error.get("message").toString());
		Map<String, Object> map = null;
		map = BeanUtils.beanToMap(resp);
		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	/**
	 * 此mapping主要隐射text/html方式请求错误视图响应，一般响应浏览器请求
	 */
	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(getStatus(request).value());
		Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
		return new ModelAndView("newerror", model);
	}

	private ResponseEntity<Map<String, Object>> notfound(HttpServletRequest request) {
		Result resp = new Result(HttpStatusEnum.NOT_FOUND, false);
		resp.setMsg("请求URL无效或者资源不存在！");
		Map<String, Object> map = BeanUtils.beanToMap(resp);
		return new ResponseEntity<Map<String, Object>>(map, getStatus(request));
	}

	private ResponseEntity<Map<String, Object>> exception(HttpServletRequest request) {
		Result resp = new Result(HttpStatusEnum.ERROR, false);
		resp.setMsg("系统错误！");
		Map<String, Object> map = BeanUtils.beanToMap(resp);
		return new ResponseEntity<Map<String, Object>>(map, getStatus(request));
	}

	private ResponseEntity<Map<String, Object>> notAuthorized(HttpServletRequest request) {
		Result resp = new Result(HttpStatusEnum.NOT_AUTHORIZED, false);
		resp.setMsg("请求未授权！");
		Map<String, Object> map = BeanUtils.beanToMap(resp);
		return new ResponseEntity<Map<String, Object>>(map, getStatus(request));
	}

	private ResponseEntity<Map<String, Object>> badRequest(HttpServletRequest request) {
		Result resp = new Result(HttpStatusEnum.BAD_REQUEST, false);
		resp.setMsg("非法请求！");
		Map<String, Object> map = BeanUtils.beanToMap(resp);
		return new ResponseEntity<Map<String, Object>>(map, getStatus(request));
	}
	
	private Result exception(Object exception,String message) {
		if(StringUtils.isNullOrEmpty(exception)){
			return null;
		}
		String className = exception.toString();
		Result result = null;
		//参数异常
		if(className.equals(ParamsException.class.getName())){
			result = new Result(HttpStatusEnum.BAD_REQUEST, false);
			result.setMsg(message);
		}
		return result;
	}
}
