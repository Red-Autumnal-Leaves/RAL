package com.ral.sms.business.member.impl;

import com.ral.exception.ParamsException;
import com.ral.model.dto.member.MemberTypeDto;
import com.ral.model.entity.member.MemberType;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.member.MemberQuery;
import com.ral.model.query.member.MemberTypeQuery;
import com.ral.model.res.Result;
import com.ral.service.member.IMemberService;
import com.ral.service.member.IMemberTypeService;
import com.ral.sms.business.member.IMemberTypeBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 2018/1/31.
 */
@Service
public class MemberTypeBusinessImpl implements IMemberTypeBusiness {

    @Autowired
    private IMemberTypeService memberTypeService;

    @Autowired
    private IMemberService memberService;

    @Override
    public Result all(HttpServletRequest request) {
        return Result.initSuccessResult(memberTypeService.getAll(),null);
    }

    @Override
    public Result query(HttpServletRequest request, MemberTypeQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize() <= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<MemberTypeDto> dtos = memberTypeService.query(query);
        query.setTotal(memberTypeService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result detail(HttpServletRequest request, Long memberTypeId) {
        return Result.initSuccessResult(memberTypeService.selectDtoById(memberTypeId),null);
    }

    @Override
    public Result save(HttpServletRequest request, String body) {
        checkRequestBody(body);
        MemberType memberType = JSONUtils.toBean(body,MemberType.class);
        if(memberTypeService.save(memberType) > 0){
            return Result.initSuccessResult(null,null);
        }else{
            return Result.initErrorResult(HttpStatusEnum.ERROR,"error");
        }
    }

    @Override
    public Result update(HttpServletRequest request,Long id, String body) {
        if(id == null || id == 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' cannot be empty");
        }
        checkRequestBody(body);
        MemberType source = memberTypeService.selectById(id);
        if(source == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ id +" does not exist");
        }
        MemberType memberType = JSONUtils.toBean(body,MemberType.class);
        memberType.setId(id);
        if(memberTypeService.update(memberType) > 0){
            return Result.initSuccessResult(null,null);
        }else{
            return Result.initErrorResult(HttpStatusEnum.ERROR,"error");
        }
    }

    @Override
    public Result del(HttpServletRequest request, Long id) {
        if(id == null || id == 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' cannot be empt");
        }
        MemberType source = memberTypeService.selectById(id);
        if(source == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ id +" does not exist");
        }
        MemberQuery query = new MemberQuery();
        query.setMemberType(id);
        int count  = memberService.queryCount(query);
        if(count > 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ id +" Already used");
        }else{
            if(memberTypeService.del(id) > 0){
                return Result.initSuccessResult(null,null);
            }else{
                return Result.initErrorResult(HttpStatusEnum.ERROR,"error");
            }
        }
    }

    private void checkRequestBody(String body){
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        MemberType memberType = JSONUtils.toBean(body,MemberType.class);
        if(StringUtils.isNullOrEmpty(memberType.getName())){
            throw new ParamsException("The 'name' cannot be empty");
        }
        if(memberType.getIsDiscount() == null){
            throw new ParamsException("The 'isDiscount' cannot be empty");
        }
        if(memberType.getIsDiscount()){
            if(memberType.getDiscount() == null || memberType.getDiscount().doubleValue() <= 0 || memberType.getDiscount().doubleValue() > 1){
                throw new ParamsException("The 'discount' must be greater than 0, less than 1");
            }
        }
    }
}
