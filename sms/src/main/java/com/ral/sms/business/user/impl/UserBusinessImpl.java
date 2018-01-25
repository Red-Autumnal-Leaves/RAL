package com.ral.sms.business.user.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.model.auth.res.Manager;
import com.ral.model.domain.AccessToken;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.res.Result;
import com.ral.service.auth.IAuthApiService;
import com.ral.service.session.ISessionService;
import com.ral.service.token.ITokenService;
import com.ral.sms.business.user.IUserBusiness;
import com.ral.sms.constants.SmsSystemConstants;
import com.ral.util.codec.BeanUtils;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.web.ActionUtil;
import com.ral.util.web.CookieUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by victor on 2018/1/25.
 */
@Service
public class UserBusinessImpl implements IUserBusiness {

    private static Logger logger  = Logger.getLogger(UserBusinessImpl.class);

    @Autowired
    private IAuthApiService authApiService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ISessionService sessionService;

    @Override
    public Result login(HttpServletRequest request, HttpServletResponse response, String body) {
        if(StringUtils.isNullOrEmpty(body)){
            return new Result(HttpStatusEnum.BAD_REQUEST, "错误的请求参数：请求体不能为空！", false, null);
        }
        if(!StringUtils.isJson(body)){
            return new Result(HttpStatusEnum.BAD_REQUEST, "错误的请求参数：请求数据格式错误！", false, null);
        }
        Map<String, Object> params = JSONUtils.toMap(body);
        if(params.get("username") == null || StringUtils.isNullOrEmpty(params.get("username").toString()) || params.get("password") == null || StringUtils.isNullOrEmpty(params.get("password").toString())){
            return new Result(HttpStatusEnum.BAD_REQUEST, "错误的请求参数：用户名或密码不能为空！", false, null);
        }
        String userName = params.get("username").toString();
        String password = params.get("password").toString();
        Result result = authApiService.login(userName, password);
        if(!result.isSuccess()){
            return result;
        }
        Manager manager = JSONUtils.toBean(JSONUtils.toJson(result.getResponse()), Manager.class);
        //删除原来的 token session
        String tokenId = ActionUtil.getToken(request);
        //token不为空
        if(!StringUtils.isNullOrEmpty(tokenId)){
            //删除token
            tokenService.removeToken(tokenId);
            //删除session
            sessionService.invalidate(tokenId);
        }

        //创建token和session
        AccessToken token = tokenService.createToken();
        CookieUtils.writeCookie(response, "token", token.getTokenId());
        sessionService.createSession(token.getTokenId());

        logger.info("create token :" + token.getTokenId());

        //保存用户信息，权限信息
        sessionService.setAttribute(token.getTokenId(), SmsSystemConstants.SESSION_USER_KEY, manager);
        logger.info("Manager logined .. [" + params.get("username") +"]");

        Map<String,Object> data = BeanUtils.beanToMap(manager);
        data.put("token", tokenId);
        result.setResponse(data);
        return result;
    }

    @Override
    public Result token(HttpServletRequest request, HttpServletResponse response) {
        String tokenId = request.getHeader("token");//获取token
        if(StringUtils.isNullOrEmpty(tokenId)){
            tokenId = CookieUtils.getCookieValue(request, "token");
        }
        Manager manager = sessionService.getAttribute(tokenId, SmsSystemConstants.SESSION_USER_KEY, Manager.class);
        Map<String,Object> data = BeanUtils.beanToMap(manager);
        data.put("token", tokenId);
        return new Result(HttpStatusEnum.OK, "success", true,data);
    }

    @Override
    public Result loginout(HttpServletRequest request, HttpServletResponse response) {
        String tokenId = ActionUtil.getToken(request);
        if(!StringUtils.isNullOrEmpty(tokenId)){//token不为空
            tokenService.removeToken(tokenId);//删除token
            sessionService.invalidate(tokenId);//删除session
            CookieUtils.removeCookie("token", request, response);//删除cookie
        }
        return new Result(HttpStatusEnum.OK, "success", true,null);
    }

}
