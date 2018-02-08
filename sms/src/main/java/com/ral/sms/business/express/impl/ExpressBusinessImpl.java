package com.ral.sms.business.express.impl;

import com.ral.exception.ParamsException;
import com.ral.model.auth.res.Manager;
import com.ral.model.entity.express.Express;
import com.ral.model.entity.express.ExpressExample;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.express.ExpressQuery;
import com.ral.model.res.Result;
import com.ral.service.express.IExpressService;
import com.ral.sms.business.express.IExpressBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Created by victor on 2018/2/1.
 */
@Service
public class ExpressBusinessImpl implements IExpressBusiness {


    @Autowired
    private IExpressService expressService;

    @Override
    public Result all(HttpServletRequest request) {
        List<Express> expresses = expressService.selectByExample(null);
        return Result.initSuccessResult(expressService.convertToDto(expresses),null);
    }

    @Override
    public Result query(HttpServletRequest request, ExpressQuery query) {
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        if(query != null ){
            if(!StringUtils.isNullOrEmpty(query.getExpressName())){
                criteria.andExpressNameLike(query.getExpressName());
            }
            if(!StringUtils.isNullOrEmpty(query.getKeywords())){
                criteria.andKeywordsLike(query.getKeywords());
            }
        }
        List<Express> expresses = expressService.selectByExample(example);
        return Result.initSuccessResult(expressService.convertToDto(expresses),query);
    }

    @Override
    public Result detail(HttpServletRequest request, Long expressId) {
        Express express = expressService.selectById(expressId);
        return Result.initSuccessResult(expressService.convertToDto(express),null);
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        checkRequestBody(body);
        Express express = JSONUtils.toBean(body,Express.class);
        express.setCreateUser(manager.getUserName());
        express.setLastUpdateUser(manager.getUserName());
        if(expressService.countByExpressName(express.getExpressName()) > 0){
            throw new ParamsException("The 'expressName' " + express.getExpressName() + " already exist");
        }
        if(expressService.save(express) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult(HttpStatusEnum.ERROR,"System error !");
    }

    @Override
    public Result update(HttpServletRequest request, Long expressId, String body, Manager manager) {
        checkRequestBody(body);
        Express update = expressService.selectById(expressId);
        if(update == null){
            throw new ParamsException("The 'expressId' " + expressId + " does not exist");
        }
        Express express = JSONUtils.toBean(body,Express.class);
        express.setId(expressId);
        update.setLastUpdateUser(manager.getUserName());
        update.setKeywords(express.getKeywords());
        if(!express.getExpressName().equals(update.getExpressName())){//名称是否修改
            if(expressService.countByExpressName(express.getExpressName()) > 0){
                throw new ParamsException("The 'expressName' " + express.getExpressName() + " already exist");
            }
            update.setExpressName(express.getExpressName());
        }
        if(expressService.update(update) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult(HttpStatusEnum.ERROR,"System error !");
    }

    private void checkRequestBody(String body){
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        Express express = JSONUtils.toBean(body,Express.class);
        if(StringUtils.isNullOrEmpty(express.getExpressName())){
            throw new ParamsException("The 'expressName' cannot be empty");
        }
        if(StringUtils.isNullOrEmpty(express.getKeywords())){
            throw new ParamsException("The 'keywords' cannot be empty");
        }
    }

}
