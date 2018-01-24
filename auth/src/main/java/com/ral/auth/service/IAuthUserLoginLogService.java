package com.ral.auth.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author victor
 * @desc
 * @date 2018/1/24 20:50
 */
public interface IAuthUserLoginLogService {

    void logger(HttpServletRequest request,String tenantId,String user);
}
