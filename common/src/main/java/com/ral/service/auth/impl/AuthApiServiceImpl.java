package com.ral.service.auth.impl;

import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.enums.auth.AuthApiEnum;
import com.ral.model.res.Result;
import com.ral.service.auth.IAuthApiService;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.http.RestTemplateUtils;
import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 2018/1/25.
 */
@Service
@Configuration
@PropertySource("classpath:ral-auth.properties")
@ConfigurationProperties(prefix = "ral.auth")
public class AuthApiServiceImpl implements IAuthApiService {

    private static Logger logger = Logger.getLogger(AuthApiServiceImpl.class);

    private String url;

    private String tenant;

    private String key;


    /**
     * 登录：
     */
    @Override
    public Result login(String userName, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);
        String body = JSONUtils.toJson(params);
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("tenantId", tenant);
        try{
            ResponseEntity<Object> response = RestTemplateUtils.requestJson(url + AuthApiEnum.LOGIN.getUrl(), AuthApiEnum.LOGIN.getMethod(), pathParams, body);
            this.info(url + AuthApiEnum.LOGIN.getUrl(), body,response);
            return getResult(response);
        }catch (Exception e) {
            e.printStackTrace();
            this.error(url, body, e);
            return new Result(HttpStatusEnum.ERROR,"系统异常,请联系管理员！",false);
        }
    }

    private void info(String url,String req,ResponseEntity<Object> response){
        logger.error("AUTH-API : 请求[" + url + "] ,REQ(" + req + "), RESP(" + JSONUtils.toJson(response) + ")");
    }

    private void error(String url,String req,Exception e){
        logger.error("AUTH-API : 请求[" + url + "] ,REQ(" + req + ") {}.",e);
    }


    private Result getResult(ResponseEntity<Object> response){
        Result result = Result.initResult();
        result.setSuccess(isSuccess(response.getStatusCodeValue()));
        result.setMsg(response.getStatusCode().name());
        result.setStatus(response.getStatusCodeValue());
        if(!StringUtils.isNullOrEmpty(response.getBody())){
            String body = JSONUtils.toJson(response.getBody());
            return JSONUtils.toBean(body, Result.class);
        }else{
            result.setResponse(response.getBody());
        }
        return result;
    }

    @Override
    public Result token(String token) {
        return null;
    }

    @Override
    public Result refresh(String token) {
        return null;
    }

    @Override
    public Result loginout(String token) {
        return null;
    }

    private boolean isSuccess(int code){
        switch(code){
            case 200:
                return true;
            case 201:
                return true;
            case 202:
                return true;
            default:return false;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
