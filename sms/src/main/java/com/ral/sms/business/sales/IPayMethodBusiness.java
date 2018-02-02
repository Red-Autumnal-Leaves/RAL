package com.ral.sms.business.sales;

import com.ral.model.query.sales.PayMethodQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/2.
 */
public interface IPayMethodBusiness {

    Result query(HttpServletRequest request, PayMethodQuery query);

    Result all(HttpServletRequest request, PayMethodQuery query);

    Result detail(HttpServletRequest request, Long methodId);

    Result update(HttpServletRequest request, Long methodId,String body);

    Result save(HttpServletRequest request,String body);

}
