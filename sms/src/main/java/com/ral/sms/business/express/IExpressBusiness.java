package com.ral.sms.business.express;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.express.ExpressQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/1.
 */
public interface IExpressBusiness {

    Result all(HttpServletRequest request);

    Result query(HttpServletRequest request, ExpressQuery query);

    Result detail(HttpServletRequest request,Long expressId);

    Result save(HttpServletRequest request, String body, Manager manager);

    Result update(HttpServletRequest request,Long expressId, String body, Manager manager);

    Result enable(HttpServletRequest request, Long expressId, Manager manager);
}
