package com.ral.sms.business.user;

import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by victor on 2018/1/25.
 */
public interface IUserBusiness {

    /**
     * 登陆
     * @param request
     * @param response
     * @param body
     * @return
     */
     Result login(HttpServletRequest request, HttpServletResponse response, String body);

    /**
     * 获取登陆用户信息
     * @param request
     * @param response
     * @return
     */
     Result token(HttpServletRequest request,HttpServletResponse response);

    /**
     * 注销
     * @param request
     * @param response
     * @return
     */
     Result loginout(HttpServletRequest request,HttpServletResponse response);
}
