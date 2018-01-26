package com.ral.sms.business.catalog;

import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/26.
 */
public interface ICatalogBusiness {

    Result tree(HttpServletRequest request);
}
