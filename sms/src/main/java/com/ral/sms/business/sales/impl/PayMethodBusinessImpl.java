package com.ral.sms.business.sales.impl;

import com.ral.model.query.sales.PayMethodQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.sales.IPayMethodBusiness;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/2.
 */
@Service
public class PayMethodBusinessImpl implements IPayMethodBusiness {

    @Override
    public Result query(HttpServletRequest request, PayMethodQuery query) {
        return null;
    }

    @Override
    public Result all(HttpServletRequest request, PayMethodQuery query) {
        return null;
    }

    @Override
    public Result detail(HttpServletRequest request, Long methodId) {
        return null;
    }

    @Override
    public Result update(HttpServletRequest request, Long methodId, String body) {
        return null;
    }

    @Override
    public Result save(HttpServletRequest request, String body) {
        return null;
    }
}
