package com.ral.sms.business.brand;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.brand.BrandQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
public interface IBrandBusiness {

    Result query(HttpServletRequest request, BrandQuery query);

    Result detail(HttpServletRequest request,Long brandId);

    Result save(HttpServletRequest request, String body, Manager manager);

    Result update(HttpServletRequest request,Long brandId, String body, Manager manager);

}
