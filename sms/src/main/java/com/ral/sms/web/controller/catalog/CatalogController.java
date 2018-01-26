package com.ral.sms.web.controller.catalog;

import com.ral.model.res.Result;
import com.ral.sms.business.catalog.ICatalogBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/26.
 */
@RestController
@RequestMapping("/catalog/*")
public class CatalogController {

    @Autowired
    private ICatalogBusiness catalogBusiness;

    @RequestMapping("/tree")
    public Result tree(HttpServletRequest request){
        return catalogBusiness.tree(request);
    }
}
