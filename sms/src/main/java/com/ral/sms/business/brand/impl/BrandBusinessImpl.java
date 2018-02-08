package com.ral.sms.business.brand.impl;

import com.ral.exception.ParamsException;
import com.ral.model.auth.res.Manager;
import com.ral.model.dto.brand.BrandDto;
import com.ral.model.entity.brand.Brand;
import com.ral.model.entity.brand.BrandExample;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.brand.BrandQuery;
import com.ral.model.res.Result;
import com.ral.service.brand.IBrandService;
import com.ral.sms.business.brand.IBrandBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 2018/2/8.
 */
@Service
public class BrandBusinessImpl implements IBrandBusiness {

    @Autowired
    private IBrandService brandService;

    @Override
    public Result query(HttpServletRequest request, BrandQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize()<= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<BrandDto> dtos = brandService.query(query);
        query.setTotal(brandService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result detail(HttpServletRequest request, Long brandId) {
        return Result.initSuccessResult(brandService.selectDtoById(brandId),null);
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        checkRequestBody(body);
        Brand brand = JSONUtils.toBean(body,Brand.class);
        BrandExample example = new BrandExample();
        example.createCriteria().andNameEqualTo(brand.getName());
        if(brandService.countByExample(example) > 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'name ' "+ brand.getName() +" already exist");
        }
        brand.setCreateUser(manager.getUserName());
        brand.setId( null);
        if(brandService.insertSelective(brand) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error");
    }

    @Override
    public Result update(HttpServletRequest request, Long brandId, String body, Manager manager) {
        checkRequestBody(body);

        Brand entity = brandService.selectById(brandId);
        if(entity == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'id' "+ brandId +" does not exist");
        }
        Brand brand = JSONUtils.toBean(body,Brand.class);
        BrandExample example = new BrandExample();
        example.createCriteria().andNameEqualTo(brand.getName()).andIdEqualTo(brandId);
        if(brandService.countByExample(example) > 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'name ' "+ brand.getName() +" already exist");
        }
        entity.setName(brand.getName());
        entity.setLastUpdateUser(manager.getUserName());
        entity.setLogo(brand.getLogo());
        if(brandService.update(entity) > 0){
            return Result.initSuccessResult(null,null);
        }
        return Result.initErrorResult("System error");
    }



    private void checkRequestBody(String body){
        if(StringUtils.isNullOrEmpty(body)){
            throw  new ParamsException("The request body cannot be empty");
        }
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        Brand brand = JSONUtils.toBean(body,Brand.class);
        if(StringUtils.isNullOrEmpty(brand.getName())){
            throw new ParamsException("The 'name' cannot be empty");
        }
    }
}
