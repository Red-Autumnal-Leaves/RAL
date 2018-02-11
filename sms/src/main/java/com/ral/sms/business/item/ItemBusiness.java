package com.ral.sms.business.item;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/9.
 */
public interface ItemBusiness {

    Result query(HttpServletRequest request, ItemQuery query);

    Result detail(HttpServletRequest request,String itemCode);

    Result specs(HttpServletRequest request,  String itemCode);

    Result skus(HttpServletRequest request, String itemCode);

    Result removeSpec(HttpServletRequest request,String itemCode,Long specId);

    Result addSpec(HttpServletRequest request, String itemCode, Long specId);

    Result save(HttpServletRequest request, String body, Manager manager);

    Result update(HttpServletRequest request,String itemCode, String body, Manager manager);




}
