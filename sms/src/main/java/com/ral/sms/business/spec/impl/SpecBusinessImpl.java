package com.ral.sms.business.spec.impl;

import com.ral.exception.ParamsException;
import com.ral.model.auth.res.Manager;
import com.ral.model.dto.spec.SpecDto;
import com.ral.model.entity.spec.Spec;
import com.ral.model.entity.spec.SpecValue;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.spec.SpecQuery;
import com.ral.model.res.Result;
import com.ral.service.spec.ISpecService;
import com.ral.sms.business.spec.ISpecBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
@Service
public class SpecBusinessImpl implements ISpecBusiness {

    @Autowired
    private ISpecService specService;

    @Override
    public Result query(HttpServletRequest request, SpecQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize()<= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<SpecDto> dtos = specService.query(query);
        query.setTotal(specService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result query(HttpServletRequest request, Long categoryId) {
        return Result.initSuccessResult(specService.selectDtoByCategoryId(categoryId), null);
    }

    @Override
    public Result detail(HttpServletRequest request, Long specId) {
        return Result.initSuccessResult(specService.selectDtoById(specId), null);
    }

    @Override
    public Result save(HttpServletRequest request, Long categoryId, String body, Manager manager) {
        checkRequestBody(body);
        SpecDto spec = JSONUtils.toBean(body,SpecDto.class);
        if(specService.insert(spec)){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error .");
    }

    //todo 目前没有校验是否已经被使用
    @Override
    public Result update(HttpServletRequest request, Long specId, String body, Manager manager) {
        checkRequestBody(body);
        Spec spec = specService.selectById(specId);
        if(spec == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ specId +" does not exist");
        }
        SpecDto dto = JSONUtils.toBean(body,SpecDto.class);
        if(spec.getCategoryId().intValue() != dto.getCategoryId().intValue()){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'categoryId' is  not allowed to modify.");
        }
        dto.setId(specId);
        if(specService.update(dto)){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error .");
    }

    //todo 目前没有校验是否已经被使用
    @Override
    public Result delete(HttpServletRequest request, Long specId) {
        Spec spec = specService.selectById(specId);
        if(spec == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ specId +" does not exist");
        }
        if(specService.delete(specId) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error .");
    }

    //todo 目前没有校验是否已经被使用
    @Override
    public Result removeValue(HttpServletRequest request, Long specId, Long valueId) {
        Spec spec = specService.selectById(specId);
        if(spec == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ specId +" does not exist");
        }
        if(specService.removeValue(specId,valueId) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error .");
    }

    private void checkRequestBody(String body){
        if(StringUtils.isNullOrEmpty(body)){
            throw  new ParamsException("The request body cannot be empty");
        }
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        SpecDto spec = JSONUtils.toBean(body,SpecDto.class);
        if(StringUtils.isNullOrEmpty(spec.getName())){
            throw new ParamsException("The spec 'name' cannot be empty");
        }
        if(spec.getCategoryId() == null || spec.getCategoryId() == 0){
            throw new ParamsException("The spec 'categoryId' cannot be empty");
        }
        if(spec.getValues() != null && !spec.getValues().isEmpty()){
            List<String> values = new ArrayList<>();
            for(SpecValue value : spec.getValues()){
                if(StringUtils.isNullOrEmpty(value.getValue())){
                    throw new ParamsException("The spec value 'value' cannot be empty");
                }
                if(values.contains(value.getValue())){
                    throw new ParamsException("The spec_value 'value ' "+ value.getValue() +" already exist");
                }
                values.add(value.getValue());
            }
        }
    }


}
