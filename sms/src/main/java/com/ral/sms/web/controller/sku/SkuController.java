package com.ral.sms.web.controller.sku;

import com.ral.model.query.sku.SkuQuery;
import com.ral.model.res.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/11.
 */
@RestController
@RequestMapping("/sku/*")
public class SkuController {


    @RequestMapping("/query")
    public Result query(HttpServletRequest request, SkuQuery query){
        return null;
    }



}
