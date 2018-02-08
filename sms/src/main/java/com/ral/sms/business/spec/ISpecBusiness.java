package com.ral.sms.business.spec;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.spec.SpecQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
public interface ISpecBusiness {

    Result query(HttpServletRequest request,SpecQuery query);

    Result query(HttpServletRequest request,Long categoryId);

    Result detail(HttpServletRequest request,Long specId);

    Result save(HttpServletRequest request, Long categoryId, String body, Manager manager);

    Result update(HttpServletRequest request,Long specId,String body, Manager manager);

    Result delete(HttpServletRequest request,Long specId);

    Result removeValue(HttpServletRequest request,Long specId,Long valueId);

}
