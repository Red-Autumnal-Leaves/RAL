package com.ral.sms.business.express.impl;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.express.ExpressQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.express.IExpressBusiness;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/1.
 */
@Service
public class ExpressBusinessImpl implements IExpressBusiness {


    @Override
    public Result all(HttpServletRequest request) {
        return null;
    }

    @Override
    public Result query(HttpServletRequest request, ExpressQuery query) {
        return null;
    }

    @Override
    public Result detail(HttpServletRequest request, Long expressId) {
        return null;
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        return null;
    }

    @Override
    public Result update(HttpServletRequest request, Long expressId, String body, Manager manager) {
        return null;
    }

    @Override
    public Result enable(HttpServletRequest request, Long expressId, Manager manager) {
        return null;
    }
}
