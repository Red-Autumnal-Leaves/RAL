package com.ral.sms.business.member;

import com.ral.model.query.member.MemberQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/29.
 */
public interface IMemberBusiness {

    Result query(HttpServletRequest request, MemberQuery query);

    Result detail(HttpServletRequest request,Long id);

    Result enable(HttpServletRequest request,Long id, Integer status);
}
