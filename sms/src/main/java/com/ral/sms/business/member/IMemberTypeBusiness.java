package com.ral.sms.business.member;

import com.ral.model.query.member.MemberTypeQuery;
import com.ral.model.res.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/31.
 */
public interface IMemberTypeBusiness {

    Result all(HttpServletRequest request);

    Result query(HttpServletRequest request, MemberTypeQuery query);

    Result detail(HttpServletRequest request , Long memberTypeId);

    Result save(HttpServletRequest request , String body);

    Result update(HttpServletRequest request , Long id ,String body);

    Result del(HttpServletRequest request , Long memberTypeId);
}
