package com.ral.auth.service.impl;

import com.ral.auth.model.vo.UserVo;
import com.ral.auth.mongo.entity.AuthUserLoginLog;
import com.ral.auth.mongo.repository.IAuthUserLoginLogRepository;
import com.ral.auth.service.IAuthUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author victor
 * @desc
 * @date 2018/1/24 20:51
 */
@Service
public class AuthUserLoginLogServiceImpl implements IAuthUserLoginLogService {


    @Autowired
    private IAuthUserLoginLogRepository authUserLoginLogRepository;

    @Override
    public void logger(HttpServletRequest request,String tenantId,String user) {
        AuthUserLoginLog log = new AuthUserLoginLog();
        log.setCreateTime(new Date());
        log.setIp(request.getRemoteAddr());
        log.setTenantId(tenantId);
        log.setUser(user);
        authUserLoginLogRepository.save(log);
    }
}
