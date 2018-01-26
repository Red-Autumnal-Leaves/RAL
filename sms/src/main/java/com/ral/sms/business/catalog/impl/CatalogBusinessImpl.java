package com.ral.sms.business.catalog.impl;

import com.ral.model.entity.catalog.CatalogExample;
import com.ral.model.res.Result;
import com.ral.service.catalog.ICatalogService;
import com.ral.sms.business.catalog.ICatalogBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/26.
 */
@Service
public class CatalogBusinessImpl implements ICatalogBusiness {

    @Autowired
    private ICatalogService catalogService;

    @Override
    public Result tree(HttpServletRequest request) {
        CatalogExample example = new CatalogExample();
        example.createCriteria().andIsEnableEqualTo(true);
        return Result.initSuccessResult(catalogService.getAllTree(example),null);
    }
}
