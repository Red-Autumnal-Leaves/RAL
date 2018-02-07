package com.ral.sms.business.sales.impl;

import com.ral.exception.ParamsException;
import com.ral.model.auth.res.Manager;
import com.ral.model.dto.sales.PayMethodDto;
import com.ral.model.entity.sales.PayMethod;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.sales.PayMethodQuery;
import com.ral.model.res.Result;
import com.ral.service.sales.IPayMethodService;
import com.ral.sms.business.sales.IPayMethodBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 2018/2/2.
 */
@Service
public class PayMethodBusinessImpl implements IPayMethodBusiness {

    @Autowired
    private IPayMethodService payMethodService;

    @Override
    public Result query(HttpServletRequest request, PayMethodQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize()<= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<PayMethodDto> dtos = payMethodService.query(query);
        query.setTotal(payMethodService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result all(HttpServletRequest request, PayMethodQuery query) {
        query.setPage(false);
        return Result.initSuccessResult(payMethodService.query(query), query);
    }

    @Override
    public Result detail(HttpServletRequest request, Long methodId) {
         return Result.initSuccessResult(payMethodService.selectDtoById(methodId), null);
    }

    @Override
    public Result update(HttpServletRequest request, Long methodId, String body, Manager manager) {
        if(methodId == null || methodId == 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' cannot be empty");
        }
        checkRequestBody(body);
        PayMethod source = payMethodService.selectById(methodId);
        if(source == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ methodId +" does not exist");
        }
        PayMethod method = JSONUtils.toBean(body,PayMethod.class);
        source.setName(method.getName());
        source.setAppId(method.getAppId());
        source.setIcon(method.getIcon());
        source.setKey(method.getKey());
        source.setMchId(method.getMchId());
        source.setLastUpdateUser(manager.getUserName());
        if(payMethodService.update(source) > 0){
            return Result.initSuccessResult(null,null);
        }else{
            return Result.initErrorResult(HttpStatusEnum.ERROR,"error");
        }
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        checkRequestBody(body);
        PayMethod method = JSONUtils.toBean(body,PayMethod.class);
        method.setCreateUser(manager.getUserName());
        if(payMethodService.save(method) > 0){
            return Result.initSuccessResult(null,null);
        }else{
            return Result.initErrorResult(HttpStatusEnum.ERROR,"error");
        }
    }


    private void checkRequestBody(String body){
        if(StringUtils.isNullOrEmpty(body)){
            throw new ParamsException("Request body cannot be empty");
        }
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        PayMethod method = JSONUtils.toBean(body,PayMethod.class);
        if(StringUtils.isNullOrEmpty(method.getName())){
            throw new ParamsException("The 'name' cannot be empty");
        }
    }
}
