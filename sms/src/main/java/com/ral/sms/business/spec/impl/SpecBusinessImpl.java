package com.ral.sms.business.spec.impl;

import com.ral.model.dto.spec.SpecDto;
import com.ral.model.query.Query;
import com.ral.model.query.spec.SpecQuery;
import com.ral.model.res.Result;
import com.ral.service.spec.ISpecService;
import com.ral.sms.business.spec.ISpecBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Result save(HttpServletRequest request, Long categoryId, String body) {
        return null;
    }

    @Override
    public Result update(HttpServletRequest request, Long specId, String body) {
        return null;
    }

    @Override
    public Result delete(HttpServletRequest request, Long specId) {
        return null;
    }

    @Override
    public Result removeValue(HttpServletRequest request, Long specId, Long valueId) {
        return null;
    }
}
