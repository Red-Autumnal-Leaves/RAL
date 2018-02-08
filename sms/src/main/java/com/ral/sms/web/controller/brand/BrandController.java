package com.ral.sms.web.controller.brand;

import com.ral.model.query.brand.BrandQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.brand.IBrandBusiness;
import com.ral.sms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/7.
 */
@RestController
@RequestMapping("/brand/*")
public class BrandController extends BaseController {

    @Autowired
    private IBrandBusiness brandBusiness;

    @RequestMapping(value =  "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, BrandQuery query){
        return brandBusiness.query(request,query);
    }

    @RequestMapping(value =  "/detail/{brandId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("brandId") Long brandId){
        return brandBusiness.detail(request,brandId);
    }

    @RequestMapping(value =  "/update/{brandId}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request,@PathVariable("brandId") Long brandId, @RequestBody String body){
        return brandBusiness.update(request,brandId,body,getUser());
    }


}
