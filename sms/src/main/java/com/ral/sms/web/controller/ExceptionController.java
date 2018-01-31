package com.ral.sms.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ral.exception.ParamsException;
import com.ral.exception.ServiceException;
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
 * @author vic
 * @desc 重写BaseController的error处理
 */
@Controller
public class ExceptionController extends BasicErrorController {


    @Autowired
    public ExceptionController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

 
    /**
     * 此mapping会拦截所有非text/html的异常,一般mapping到非浏览器请求
     * 特殊处理switch
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
    	Map<String, Object> error = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.ALL));
    	HttpStatus status = getStatus(request);
		switch(status.value()){
			case 400:
				return this.badRequest(request);
			case 403:
    			return this.notAuthorized(request);
    		case 404:
    			return this.notfound(request);
    		case 500:

    			return this.exception(request,error);
		}
    	//default
    	Result resp = new Result(HttpStatusEnum.ERROR,false);
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
        Map<String, Object> model = getErrorAttributes(request,isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("newerror", model);
    }

    private ResponseEntity<Map<String, Object>> notfound(HttpServletRequest request){
    	Result resp = new Result(HttpStatusEnum.NOT_FOUND, false);
    	resp.setMsg("请求URL无效或者资源不存在！");
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


	private ResponseEntity<Map<String, Object>> exception(HttpServletRequest request,Map<String,Object> error){
		String exceptionClassName = error.get("exception").toString();
		String exceptionMessage = error.get("message").toString();
		if(!StringUtils.isNullOrEmpty(exceptionClassName)){
			if(exceptionClassName.equalsIgnoreCase(ParamsException.class.getName())){//参数异常
				Result resp = Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,exceptionMessage);
				return new ResponseEntity<Map<String, Object>>(BeanUtils.beanToMap(resp), getStatus(request));
			}
			if(exceptionClassName.equalsIgnoreCase(ServiceException.class.getName())){//系统异常
				Result resp = Result.initErrorResult(HttpStatusEnum.ERROR,"Service Exception!");
				return new ResponseEntity<Map<String, Object>>(BeanUtils.beanToMap(resp), getStatus(request));
			}
		}
		Result resp = new Result(HttpStatusEnum.ERROR, false);
		return new ResponseEntity<Map<String, Object>>(BeanUtils.beanToMap(resp), getStatus(request));
	}




}
